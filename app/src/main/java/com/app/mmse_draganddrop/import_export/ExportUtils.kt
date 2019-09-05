package com.app.mmse_draganddrop.import_export

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.EditText
import com.app.mmse_draganddrop.demo.label.Label2
import com.app.mmse_draganddrop.extras.Utils

class ExportUtils(private val ctx: Context) {

    companion object { var frameState = arrayListOf<Any>() /* Stores the current frame state */ }

    //Data for the 'Individual' Frame State -> Returns Whole Value in String
    private fun stateOfIndividualFrame(): String {
        var finalString = ""
        frameState.forEach {
            //todo -> fIX FOR NOW HERE
            if (it is Label2) {
                val labelIndividually = (it as Label2).getState()
                Log.d("check3", "${(it as Label2).getState()}")
                finalString += "$labelIndividually\n" //Moving to new line after every line processed
            } else {
                val labelIndividually = it
                Log.d("check3", "$it")
                finalString += "$labelIndividually\n" //Moving to new line after every line processed
            }


        }
        return finalString
    }

    fun showExportDialog() {
        val userFileNameInputEditText = EditText(ctx)
        userFileNameInputEditText.requestFocus() //Getting focus once Export Dialog loads up
        Utils(ctx).showSoftKeyboard(ctx) //Showing the Soft Keyboard

        AlertDialog.Builder(ctx).run {
            setTitle("Enter A File Name")
            setMessage("This will be exported to the internal storage of your phone.")
            setView(userFileNameInputEditText)
            setPositiveButton("Export") { _, _ ->
                Utils(ctx).hideSoftKeyboard(ctx, userFileNameInputEditText) //Hiding Soft Keyboard Once Button Pressed
                val fileNameUserInput = userFileNameInputEditText.text.toString()
                when { userFileNameInputEditText.text.isEmpty() -> Utils(ctx).longToast("File Name Cannot Be Empty!")
                    else -> { //Creating new .txt file with userFileNameInput and putting the data of Complete Individual Frame in that file
                        handleExportingProcess(fileNameUserInput, stateOfIndividualFrame()) //todo -> frameState is being saved [meaning a full individual frame]
                        //val a = stateOfIndividualFrame(); Log.d("check3->receive", a)
                    }
                }
            }
            setNegativeButton("Cancel") { _, _ -> Utils(ctx).hideSoftKeyboard(ctx, userFileNameInputEditText); Utils(ctx).toast("Cancelled") } //Hiding Soft Keyboard Once Button Pressed
            show()
        }
    }

    private fun handleExportingProcess(fileNameUserInput: String, messageExported: String) {
        val fileNameslist = FileHelper(ctx).getTxtFilesList()
        if (fileNameUserInput in fileNameslist) { //Already Exists
            FileHelper(ctx).showOverwriteDialog(fileNameUserInput, messageExported) //Ask User Whether They Want to Overwrite The File or Not
        } else { //Does Not Exist
            FileHelper(ctx).write(fileNameUserInput, messageExported) //Write It
            Utils(ctx).toast("Export Complete")
        }
    }

}