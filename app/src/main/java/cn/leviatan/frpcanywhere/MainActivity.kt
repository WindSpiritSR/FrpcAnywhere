package cn.leviatan.frpcanywhere

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.FileObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import cn.leviatan.frpcanywhere.ui.theme.FrpcAnywhereTheme
import cn.leviatan.frpcanywhere.utils.Storage
import frpclib.Frpclib
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
    private lateinit var logFileWatcher: LogFileObserver
    private var logStr by mutableStateOf("")
    private val maxLogLines = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissionList = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
        )

        for (permission in permissionList) {
            val checkSelfPermission = ActivityCompat.checkSelfPermission(this, permission)
            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(permission), 1)
            }
        }

        var startBtnEnable by mutableStateOf(true)
        var startBtnText by mutableStateOf("Start")

        runLogs()

        setContent {
            FrpcAnywhereTheme {
                Surface {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(15.dp)) {
                        Button(onClick = {
                            startBtnEnable = false
                            startBtnText = "Stop"
                            thread(name = "frpc") {
                                runFrpc()
                            }
                        },
                        content = { Text(text = startBtnText)})

                        LogArea()
                    }
                }
            }
        }
    }

    private fun runFrpc() {
        val storage = Storage(this)
        try {
            Frpclib.run(storage.confFilePath())
        } catch (e: Exception) {
//          TODO: Check exception type
//          TODO: Show error message
            e.printStackTrace()
        }
    }

    private fun runLogs() {
        val storage = Storage(this)
        var logFileEvents: ArrayList<Int>
        logFileWatcher = LogFileObserver(storage.confFilePath())
        logFileWatcher.startWatching()
        thread(name = "logs") {
            while (true) {
                logFileEvents = waitForEvent(logFileWatcher)
                if (logFileEvents.size > 0) {
                    for (event in logFileEvents) {
                        when(event) {
                            FileObserver.MODIFY -> {
//                                TODO: Read and check log file
                                println("==================== Log File MODIFY ====================")
                            }
                        }
                    }
                }
                Thread.sleep(100)
            }
        }
    }

    @Composable
    private fun LogArea() {
        var textFeildStr = ""
        Column {
            OutlinedTextField(
                value = textFeildStr,
                onValueChange = {},
                label = { Text("Log") },
                readOnly = true,
                maxLines = 5,
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    private fun waitForEvent(logFileObserver: LogFileObserver): ArrayList<Int> {
        synchronized(logFileObserver) {
            return logFileObserver.getEvents()
        }
    }

    private class LogFileObserver(path: String) : FileObserver(path) {
        private var observerEvents = ArrayList<Int>()

        @Synchronized
        override fun onEvent(event: Int, path: String?) {
            observerEvents.add(event)
        }

        @Synchronized
        fun getEvents(): ArrayList<Int> {
            val event = ArrayList<Int>()
            event.addAll(observerEvents)
            observerEvents.clear()
            return event
        }
    }
}
