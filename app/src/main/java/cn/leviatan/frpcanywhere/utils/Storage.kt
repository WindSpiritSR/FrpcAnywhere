package cn.leviatan.frpcanywhere.utils

import android.content.Context
import java.io.File

class Storage (private val context: Context){
    private val configFileName = "frpc.ini"

    init {
        val confFile = File(confFilePath())
        if (!confFile.exists()) {
            File(rootFilesDir(), confFileName()).printWriter()
        }
    }

    fun externalFilesDir(): String? {
        return context.getExternalFilesDir("")?.absolutePath
    }

    fun rootFilesDir(): String? {
        return if (true) externalFilesDir() else context.filesDir.absolutePath
    }

    fun confFileName(): String {
        return configFileName
    }

    fun confFilePath(): String {
        return rootFilesDir() + "/" + configFileName
    }
}