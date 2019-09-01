package com.app.mmse_draganddrop.extras

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.EditText
import com.app.mmse_draganddrop.demo.label.Label2

class ExportUtils(private val ctx: Context) {

    companion object {
        var frameState = arrayListOf<Any>() //Stores the current frame state
    }

    //Data for the 'Individual' Frame State -> Returns Whole Value in String
    fun stateOfIndividualFrame(): String {
        var finalString = ""
        frameState.forEach {
            //Log.d("check3", "${(it as Label2).getState()}")
            val labelIndividually = (it as Label2).getState()
            finalString += "$labelIndividually\n" //Moving to new line after every line processed
        }
        return finalString
    }

    fun showExportDialog() {
        val exportAlertDialog = AlertDialog.Builder(ctx)
        val userFileNameInputEditText = EditText(ctx)
        exportAlertDialog.setTitle("Enter A File Name")
        exportAlertDialog.setMessage("This will be exported to the internal storage of your phone.")
        exportAlertDialog.setView(userFileNameInputEditText)

        userFileNameInputEditText.requestFocus() //Getting focus once Export Dialog loads up
        Utils(ctx).showSoftKeyboard(ctx) //Showing the Soft Keyboard

        exportAlertDialog.setPositiveButton("Export") { _, _ ->
            Utils(ctx).hideSoftKeyboard(ctx, userFileNameInputEditText) //Hiding Soft Keyboard Once Button Pressed

            val fileNameUserInput = userFileNameInputEditText.text.toString()

            if (userFileNameInputEditText.text.isEmpty()) {
                Utils(ctx).longToast("File Name Cannot Be Empty!")
            } else {
                //Creating new .txt file with userFileNameInput and putting the data of Complete Individual Frame in that file
                ExportUtils(ctx).handleExportingProcess(fileNameUserInput, stateOfIndividualFrame()) //todo -> frameState is being saved [meaning a full individual frame]

                val a = stateOfIndividualFrame()
                Log.d("check3->receive", a)
            }

        }
        exportAlertDialog.setNegativeButton("Cancel") { _, _ ->
            Utils(ctx).hideSoftKeyboard(ctx, userFileNameInputEditText) //Hiding Soft Keyboard Once Button Pressed

            Utils(ctx).toast("Cancelled")
        }
        exportAlertDialog.show()
    }

    fun handleExportingProcess(fileNameUserInput: String, messageExported: String) {
        val fileNameslist = getTxtFilesList()//.forEach { if (".txt" in it) { it.dropLast(4) } //removes .txt if exists }

        if (fileNameUserInput in fileNameslist) { //Already Exists
            showOverwriteDialog(fileNameUserInput, messageExported) //Ask User Whether They Want to Overwrite The File or Not
        } else { //Does Not Exist
            FileHelper(ctx).write(fileNameUserInput, messageExported) //Write It
            Utils(ctx).toast("Export Complete")
        }
    }

    fun showOverwriteDialog(fileNameUserInput: String, messageExported: String) {
        val overwriteAlertDialog = AlertDialog.Builder(ctx)
        overwriteAlertDialog.setTitle("Do you want to overwrite?")
        overwriteAlertDialog.setMessage("This file name already exists in internal storage of your phone. Press 'Yes' if you want to overwrite it.")
        overwriteAlertDialog.setPositiveButton("Overwrite") { _, _ -> Utils(ctx).toast("Done"); overwrite(fileNameUserInput, messageExported) }
        overwriteAlertDialog.setNegativeButton("Cancel") { _, _ -> Utils(ctx).toast("Cancelled") }
        overwriteAlertDialog.show()
    }

    fun overwrite(fileNameUserInput: String, messageExported: String) {
        FileHelper(ctx).write(fileNameUserInput, messageExported)
    }

    fun getTxtFilesList(): ArrayList<String> {
        val fileNamesList = arrayListOf<String>()
        val txtFilesList = FileHelper(ctx).getInfo()
        if (txtFilesList != null) {
            for (file in txtFilesList) {
                Log.d("txt-files-list", file.name)
                fileNamesList.add(file.name)
            }
        }
        return fileNamesList
    }

}