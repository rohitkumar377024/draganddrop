package com.app.mmse_draganddrop.command

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.draganddrop.R
import kotlinx.android.synthetic.main.activity_command_preview.*

class CommandPreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_preview)

        //Getting Input Command
        val inputCommand = intent.getStringExtra("command")
        //Retrieving Main Blocks of Command using Regex
        val mainBlocksOfCommand = CmdUtils(this).regexMainBlock(inputCommand)
        //Looping through each Main Block
        mainBlocksOfCommand.forEach { mainBlock -> CmdUtils(this).processMainBlock(preview_root_layout, mainBlock) }
    }

//    private fun processDimensionProperty(value: String) {
//        val widthHeightSplit = value.split(",")
//
//        var width: Int? = null
//        var height: Int? = null
//
//        widthHeightSplit.forEach {
//            val propertyValueSplit = it.split(MULTIPLE_VALUES_DELIMITER)
//            when {
//                propertyValueSplit[PROPERTY] == "width" -> width = propertyValueSplit[VALUE].toInt()
//                propertyValueSplit[PROPERTY] == " height" -> height = propertyValueSplit[VALUE].toInt()
//                    //todo -> one space before left -> better solution needed
//            }
//        }
//
//        setWidthHeight(width!!, height!!)
//    }
//
//    private fun processPositionProperty(value: String) {
//        Log.d("processor", "position -> $value")
//
//        val topLeftSplit = value.split(",")
//
//        var top : Int? = null
//        var left : Int? = null
//
//        topLeftSplit.forEach {
//            val propertyValueSplit = it.split(MULTIPLE_VALUES_DELIMITER)
//            for (x in propertyValueSplit) {
//                Log.d("testerx", x)
//            }
//            when {
//                propertyValueSplit[PROPERTY] == "top" -> {
//                    top = propertyValueSplit[VALUE].toInt()
//                    Log.d("checker2", "top -> $top")
//                }
//                propertyValueSplit[PROPERTY] == " left" -> { //todo -> one space before left -> better solution needed
//                    left = propertyValueSplit[VALUE].toInt()
//                    Log.d("checker2", "left -> $left")
//                }
//            }
//        }
//
//         setPosition(top!!, left!!)
//    }
//
//    var width: Int? = null
//    var height: Int? = null
//
//    private fun setWidthHeight(passedWidth: Int, passedHeight: Int) {
//        width = Utils(this).dpToPx(passedWidth)
//        height = Utils(this).dpToPx(passedHeight)
//
//        val params = RelativeLayout.LayoutParams(width!!, height!!)
//
//        preview_dummy_txtview.layoutParams = params
//    }
//
//
//    private fun setPosition(passedTop: Int, passedLeft: Int) {
//        val topMargin = Utils(this).dpToPx(passedTop)
//        val leftMargin = Utils(this).dpToPx(passedLeft)
//
//        val params = RelativeLayout.LayoutParams(width!!, height!!)
//        params.topMargin = topMargin
//        params.leftMargin = leftMargin
//        //setting layout params here
//        preview_dummy_txtview.layoutParams = params
//    }
}
