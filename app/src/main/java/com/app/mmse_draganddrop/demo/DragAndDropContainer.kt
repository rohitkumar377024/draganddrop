package com.app.mmse_draganddrop.demo

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.*
import com.app.draganddrop.R
import com.app.mmse_draganddrop.import_export.FileHelper
import com.app.mmse_draganddrop.demo.label.Label2
import com.app.mmse_draganddrop.demo.play_audio_file.PlayAudioFile
import android.app.AlertDialog
import com.app.mmse_draganddrop.command.LabelCmd
import com.app.mmse_draganddrop.import_export.ExportUtils
import com.app.mmse_draganddrop.import_export.ImportUtils

class DragAndDropContainer: RelativeLayout {

    constructor(context: Context?) : super(context) { setupContainer() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { setupContainer() }
    @SuppressLint("ClickableViewAccessibility") private fun setupContainer() { inflate(context, R.layout.drag_and_drop_container, this) }

    //Main Export Function
    fun export() { ExportUtils(context).showExportDialog() }

    //Main Import Function
    fun import() {
        //Showing list of files from which one can be imported
        val fileNamesList = FileHelper(context).getTxtFilesList()
        val fileNamesListCS = fileNamesList.toArray (arrayOfNulls<CharSequence>(fileNamesList.size))

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
        //todo -> also cleaning the frameState for exporting later on -> might have 0 effect but... yeah
        ExportUtils.frameState.clear()

        val messageImported = FileHelper(context).read(fileName)
        Log.d("messageImported", messageImported)
        val allTheLabels = ImportUtils(context).regexMainBlock(messageImported)

        //todo todo todo
        //todo -> fixing import-export finally here
        //todo -> most important thing going on here
        //val labelCmdList = arrayListOf<LabelCmd>()
        allTheLabels.forEach {
           val labelCmdForm = ImportUtils(context).getBackLabelCmdForm(it)
            ExportUtils.frameState.add(labelCmdForm)

            Log.d("allTheLabels", it)
        }

        ExportUtils.frameState.forEach { Log.d("import->framestate", it.toString()) }

        //ExportUtils.frameState =

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

    private fun clearContainer() { removeAllViews() } /* Clearing the Container */

    fun addLabelOriginal() {
        val labelNew = Label2(context)
        addView(labelNew)
        ExportUtils.frameState.add(labelNew) //todo -> moved frameState to ExportUtils
    }

    fun addPlayAudioFileOriginal() = addView(PlayAudioFile(context))
}