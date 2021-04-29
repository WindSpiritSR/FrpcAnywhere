package cn.leviatan.frpcanywhere

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.core.app.ActivityCompat
import cn.leviatan.frpcanywhere.ui.theme.FrpcAnywhereTheme
import cn.leviatan.frpcanywhere.utils.Storage
import frpclib.Frpclib
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
    private var logStr by mutableStateOf(arrayListOf(""))
    private var maxLogLines = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissionList = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
        )

        for (permission in permissionList) {
            val checkSelfPermission = ActivityCompat.checkSelfPermission(this, permission)
            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(permission), 1)
            }
        }

        thread(name = "frpc") {
            runFrpc()
        }

        thread(name = "showLog") {
            runShowLog()
        }

        setContent {
            FrpcAnywhereTheme {
                Surface(color = MaterialTheme.colors.background) {
                    LogArea()
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

    private fun runShowLog() {
        val startTime = SimpleDateFormat("MM-dd HH:mm:ss.SSS").format(Date())
        println("============================================================ $startTime")
        val logcatCmd = arrayOf("logcat", "GoLog:I", "*:S", "-v", "raw", "-t", startTime)
        val buf = BufferedReader(InputStreamReader(Runtime.getRuntime().exec(logcatCmd).inputStream))
        var bufLine: String?

        for (i in 1..3) {
            buf.readLine()
        }

        while(true) {
//            FIXME: buf.readLine() always null
            if (buf.readLine().also { bufLine = it } != null) {
//                if (logStr.size == maxLogLines) {
//                    logStr.removeFirst()
//                }

                logStr.add(bufLine!!.replace("\\x1b\\[[0-9;]*m".toRegex(), "") + "\n")
                println("=================================================== ${logStr.last()}")
            }
        }
    }

    @Composable
    fun LogArea() {
        Column {
            logStr.forEach {
                Text(text = it)
            }
        }
    }
}
