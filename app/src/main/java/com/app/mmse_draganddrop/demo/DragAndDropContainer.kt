package com.app.mmse_draganddrop.demo

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.*
import com.app.draganddrop.R
import com.app.mmse_draganddrop.extras.FileHelper
import com.app.mmse_draganddrop.demo.label.Label2
import com.app.mmse_draganddrop.demo.play_audio_file.PlayAudioFile
import android.app.AlertDialog
import com.app.mmse_draganddrop.demo.label.LabelTouchListener
import com.app.mmse_draganddrop.extras.ExportUtils
import com.app.mmse_draganddrop.extras.ImportUtils
import com.app.mmse_draganddrop.extras.Utils

class DragAndDropContainer: RelativeLayout {

    constructor(context: Context?) : super(context) { setupContainer() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { setupContainer() }

    @SuppressLint("ClickableViewAccessibility") private fun setupContainer() {
        inflate(context, R.layout.drag_and_drop_container, this)
        //export_to_txt_file_btn.setOnClickListener { export() }
        //import_from_txt_file_btn.setOnClickListener { import() }
    }

    //main export function
    fun export() {
        ExportUtils(context).showExportDialog()
    }

    //main import function
    fun import() {
        //Showing list of files from which one can be imported
        val fileNamesList= ExportUtils(context).getTxtFilesList()
        val fileNamesListCS = fileNamesList.toArray(arrayOfNulls<CharSequence>(fileNamesList.size))

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Which File to Import")
        builder.setItems(fileNamesListCS) { _, which -> //Click Handled Here
            val clickedFileName = fileNamesListCS[which]
            startImportProcess(clickedFileName as String) //now lets import :) yay :D
        }
        builder.show()
    }

    private fun startImportProcess(fileName: String) {
        //todo -> clearing container first before importing
        clearContainer()

        val messageImported = FileHelper(context).read(fileName)
        val allTheLabels = ImportUtils(context).regexMainBlock(messageImported)

        for (labelIndividually in allTheLabels) {
            //TODO - FINISHING THE IMPORTED LAYOUT STUFF HERE
            val importedLabel = ImportUtils(context).processLabelsIndividually(labelIndividually)
            for ((view, params) in importedLabel) {

                //view.setOnTouchListener(LabelTouchListener(view))

                addView(view, params)
                Log.d("xaz", importedLabel.toString())
            }
        }
    }

    fun clearContainer() { //Used in ImportUtils
        removeAllViews()
    }

    fun addLabelOriginal() {
        val labelNew = Label2(context)
        addView(labelNew)
        ExportUtils.frameState.add(labelNew) //todo -> moved frameState to ExportUtils
    }

    fun addPlayAudioFileOriginal() = addView(PlayAudioFile(context))
}