package cn.leviatan.frpcanywhere.utils

import android.content.Context

class Storage{
    private val configFileName = "frpc.ini"

    fun externalFilesDir(context: Context): String? {
        return context.getExternalFilesDir("")?.absolutePath
    }

    fun rootFilesDir(context: Context): String {
        return context.filesDir.absolutePath
    }

    fun confFileName(): String {
        return configFileName
    }

    fun confFilePath(context: Context): String {
        return rootFilesDir(context) + "/" + configFileName
    }
}