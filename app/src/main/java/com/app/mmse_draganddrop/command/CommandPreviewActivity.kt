package com.app.mmse_draganddrop.command

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.draganddrop.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class CommandPreviewActivity : AppCompatActivity() {

//    companion object {
//        const val PROPERTY_VALUE_DELIMITER = "="
//        const val MULTIPLE_VALUES_DELIMITER = ":"
//        //Constants for Property Name and Value Identification
//        const val PROPERTY = 0
//        const val VALUE = 1
//    }

    //Creating Object from Command Class
    private val command = Command()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_preview)

        //processCommand()
        Log.d("preview", getCommandLineByLine().toString())

        val s = intent.getStringExtra("command")

        val pattern1 = "<" //first pattern left side
        val pattern2 = ">" //second pattern right side
//        val text = "sdfjsdkhfkjsdf <text=Something> \n <textSize=24> \n sdf sdkjfhsdkf"

        //todo -> Pattern.DOTALL below is essential to make this Regex work for MULTILINE!
        val p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2), Pattern.DOTALL)

        val m = p.matcher(s)
        while (m.find()) {
            Log.d("REGEX2", m.group(1))
        }
    }

        /* Handles Processing of Command */
    private fun processCommand() {
            val s = intent.getStringExtra("command")
            Log.d("fullcommand->before", s)

//            s = s.substring(s.indexOf("(") + 1)
//            s = s.substring(0, s.indexOf(")"))

//            val result = s.substringAfter("(").substringBefore(')')

//            val start = "["
//            val end = "]"
//            val pattern = Pattern.compile("$start([^>]*)$end")
//
//            val myString =
//                "What is <Mytag a exp 5 exp 3> written as a single power of <i>a</i> <Mytag yx4> and the double power of <b>x+y</b> <Mytag 3xy4>"
//            val matcher = pattern.matcher(s)
//
//            while (matcher.find()) {
//                Log.d("fullcommand->after", matcher.group(1))
//            }
        }
//        getCommandLineByLine().forEach { //Working with Each Line of Command
//            //Trimming Line by Line
////            val trimmedCommand= it.trim()
//
////            command.checkIfNewCommand(trimmedCommand)
//
////            //Passing the trimmed one now
////            val propertyValueSplit = trimmedCommand.split(PROPERTY_VALUE_DELIMITER) //format -> property=something
////            //Checking Which Property and Passing Values to their Respective Functions
////            when (propertyValueSplit[PROPERTY]) {
//////                "text" -> processTextProperty(propertyValueSplit[VALUE])
//////                "textSize" -> processTextSizeProperty(propertyValueSplit[VALUE])
//////                "fontWeight" -> processFontWeightProperty(propertyValueSplit[VALUE])
//////                "dimension" -> processDimensionProperty(propertyValueSplit[VALUE])
//////                "position" -> processPositionProperty(propertyValueSplit[VALUE])
////            }
//        }


    /* Getting Command Line-By-Line through Intent and Applying lines() function */
    private fun getCommandLineByLine() = intent.getStringExtra("command").lines()
//    private fun processTextProperty(value: String) { preview_dummy_txtview.text = value }
//    private fun processTextSizeProperty(value: String) { preview_dummy_txtview.textSize = value.toFloat() }
//    private fun processFontWeightProperty(value: String) {
//        when (value) {
//            "Thin", "thin" -> preview_dummy_txtview.typeface = Utils(this).typefaces[Utils.TYPEFACE_THIN]
//            "Light", "light" -> preview_dummy_txtview.typeface = Utils(this).typefaces[Utils.TYPEFACE_LIGHT]
//            "Medium", "medium" -> preview_dummy_txtview.typeface = Utils(this).typefaces[Utils.TYPEFACE_MEDIUM]
//            "Bold", "bold" -> preview_dummy_txtview.typeface = Utils(this).typefaces[Utils.TYPEFACE_BOLD]
//        }
//    }
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
