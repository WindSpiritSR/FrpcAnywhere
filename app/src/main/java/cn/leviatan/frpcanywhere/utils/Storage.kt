package cn.leviatan.frpcanywhere.utils

import android.content.Context
import java.io.File

class Storage (private val context: Context){
    private val configFileName = "frpc.ini"
    private val configFile: File

    init {
        configFile = File(confFilePath())
        if (!confFile().exists()) {
            File(rootFilesDir(), confFileName()).printWriter()
//            TODO: Build basic config
        }
    }

    fun externalFilesDir(): String? {
        return context.getExternalFilesDir("")?.absolutePath
    }

    fun rootFilesDir(): String? {
        return externalFilesDir()
//        return context.filesDir.absolutePath
    }

    fun confFileName(): String {
        return configFileName
    }

    fun confFile(): File {
        return configFile
    }

    fun confFilePath(): String {
        return rootFilesDir() + "/" + configFileName
    }
}