package com.app.mmse_draganddrop.command

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.draganddrop.R
import com.app.mmse_draganddrop.Utils
import java.util.regex.Pattern

class CommandPreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_preview)

        //Getting Input Command
        val inputCommand = intent.getStringExtra("command")

        //Retrieving Main Blocks of Command using Regex
        val mainBlocksOfCommand = CmdUtils().regexMainBlock(inputCommand)

        //Looping through each Main Block
        mainBlocksOfCommand.forEach { mainBlock -> processMainBlock(mainBlock) }
    }

    /* Processing Each Main Block */
    @SuppressLint("DefaultLocale")
    private fun processMainBlock(mainBlock: String) {
        //Splitting Each Main Block in Lines
        val propertyValueList = mainBlock.lines()

        //Getting the type -> It is always the first line in Main Block
        //Substring from start to ':' //Example -> 'Label:' returns 'Label'
        val type = propertyValueList[0].substring(0, propertyValueList[0].indexOf(":"))

        //Making it Lowercase so that one condition only needed //Eg. for Label, only 'label' check is needed then
        when (type.toLowerCase()) {
            "label" -> {
                var text = "" //Will store Text value for Label
                var textSize = 0f //Will store Text Size value for Label

                //Looping through All Property-Value pairs
                for (x in 1 until propertyValueList.size) {
                    //Getting a Specific Property-Value Pair
                    val specificPropertyValue = propertyValueList[x].trim()
                    //Splitting it -> [0] is Property Name and [1] is Value
                    val propertyValueSplitted = specificPropertyValue.split("=")

                    //Checking Which Property It Is
                    when(propertyValueSplitted[0].toLowerCase()) { //Checking Property Name by [0] -> Value is stored at [1]
                        "text" -> text = propertyValueSplitted[1]
                        "textsize" -> textSize = propertyValueSplitted[1].toFloat()
                    }
                    //Logs All The Property-Value Pairs
                    Log.d("testx -> details", propertyValueSplitted.toString())
                }

                //Create Label with Assigned Values Above
                createLabel(text, textSize)
            }
        }
    }

    private fun createLabel(textInput: String, textSizeInput: Float) {
        //Existing Relative Layout
        val root: RelativeLayout = findViewById(R.id.preview_root_layout)

        //Create New TextView
        val tv = TextView(this).apply {
            text = textInput
            textSize = textSizeInput
            gravity = Gravity.CENTER
            typeface = Utils(applicationContext).typefaces[Utils.TYPEFACE_LIGHT]
        }

        //Makes Width and Height Wrap Content
        val params = RelativeLayout.LayoutParams(RelativeLayout
            .LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

        //Adding the Newly Created TextView
        root.addView(tv, params)
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
//        setPosition(top!!, left!!)
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
