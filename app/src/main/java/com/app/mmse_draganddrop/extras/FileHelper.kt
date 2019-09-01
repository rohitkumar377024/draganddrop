package com.app.mmse_draganddrop.extras

import android.content.Context
import java.io.*

class FileHelper(private val ctx: Context) {
    fun write(fileName: String, message: String) = ctx.openFileOutput(fileName, Context.MODE_PRIVATE).use { it.write(message.toByteArray()) }
    fun read(fileName: String): String = File(ctx.filesDir, fileName).readText()
    fun getInfo(): Array<out File>? = ctx.filesDir.listFiles()
}