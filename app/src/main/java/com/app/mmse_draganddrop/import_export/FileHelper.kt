package com.app.mmse_draganddrop.import_export

import android.app.AlertDialog
import android.content.Context
import com.app.mmse_draganddrop.extras.Utils
import java.io.*

class FileHelper(private val ctx: Context) {

    /* Read and Write Operations */
    fun read(fileName: String): String = File(ctx.filesDir, fileName).readText()
    fun write(fileName: String, message: String) = ctx.openFileOutput(fileName, Context.MODE_PRIVATE).use { it.write(message.toByteArray()) }

    /* Get All The .txt Files */
    fun getTxtFilesList(): ArrayList<String> {
        val fileNamesList = arrayListOf<String>()
        ctx.filesDir.listFiles()?.forEach { file -> fileNamesList.add(file.name) }
        return fileNamesList
    }

    /* Overwrite Operation */
    fun showOverwriteDialog(fileNameUserInput: String, messageExported: String) {
        AlertDialog.Builder(ctx).run {
            setTitle("Do you want to overwrite?")
            setMessage("This file name already exists in internal storage of your phone. Press 'Yes' if you want to overwrite it.")
            setPositiveButton("Overwrite") { _, _ -> Utils(ctx).toast("Done"); FileHelper(ctx).write(fileNameUserInput, messageExported) } //Overwriting operation
            setNegativeButton("Cancel") { _, _ -> Utils(ctx).toast("Cancelled") }
            show()
        }
    }

}