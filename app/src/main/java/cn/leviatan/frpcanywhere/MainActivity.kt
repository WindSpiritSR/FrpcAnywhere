package cn.leviatan.frpcanywhere

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import cn.leviatan.frpcanywhere.ui.theme.FrpcAnywhereTheme
import cn.leviatan.frpcanywhere.utils.Storage
import frpclib.Frpclib
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.lang.reflect.Executable
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
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

        setContent {
            FrpcAnywhereTheme {
                Surface(color = MaterialTheme.colors.background) {
                    TextTest("Frpc Anywhere")
                }
            }
        }
    }

    fun runFrpc() {
        val storage = Storage()

        val confFile = File(storage.confFilePath(this))
        if (!confFile.exists()) {
            File(storage.rootFilesDir(this), storage.confFileName()).printWriter()
        }

        try {
            Frpclib.run(storage.confFilePath(this))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@Composable
fun TextTest(name: String) {
    Text(text = name)
}