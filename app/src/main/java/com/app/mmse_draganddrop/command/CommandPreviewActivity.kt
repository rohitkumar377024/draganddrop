package com.app.mmse_draganddrop.command

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.app.draganddrop.R
import com.app.mmse_draganddrop.Utils
import kotlinx.android.synthetic.main.activity_command_preview.*

class CommandPreviewActivity : AppCompatActivity() {

    companion object {
        const val PROPERTY_VALUE_DELIMITER = "="
        const val MULTIPLE_VALUES_DELIMITER = ":"
        //Constants for Property Name and Value Identification
        const val PROPERTY = 0
        const val VALUE = 1
    }

    //ArrayList for Typefaces
    private var typefaces= arrayListOf<Typeface?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_preview)

        /* Initializing the ArrayList storing Typefaces */
        initTypefaces()

        processCommand()

        Log.d("preview", getCommandLineByLine().toString())
    }

        /* Handles Processing of Command */
    private fun processCommand() {
        getCommandLineByLine().forEach { //Working with Each Line of Command
            val propertyValueSplit = it.split(PROPERTY_VALUE_DELIMITER) //format -> property = something
            //Checking Which Property and Passing Values to their Respective Functions
            when (propertyValueSplit[PROPERTY]) {
                "text" -> processTextProperty(propertyValueSplit[VALUE])
                "textSize" -> processTextSizeProperty(propertyValueSplit[VALUE])
                "fontWeight" -> processFontWeightProperty(propertyValueSplit[VALUE])
                "dimension" -> processDimensionProperty(propertyValueSplit[VALUE])
                "position" -> processPositionProperty(propertyValueSplit[VALUE])
            }
        }
    }

    private fun processTextProperty(value: String) { preview_dummy_txtview.text = value }
    private fun processTextSizeProperty(value: String) { preview_dummy_txtview.textSize = value.toFloat() }
    private fun processFontWeightProperty(value: String) {
        when (value) {
            "Thin" -> preview_dummy_txtview.typeface = typefaces[Utils.TYPEFACE_THIN]
            "Light" -> preview_dummy_txtview.typeface = typefaces[Utils.TYPEFACE_LIGHT]
            "Medium" -> preview_dummy_txtview.typeface = typefaces[Utils.TYPEFACE_MEDIUM]
            "Bold" -> preview_dummy_txtview.typeface = typefaces[Utils.TYPEFACE_BOLD]
        }
    }
    private fun processDimensionProperty(value: String) {
        val widthHeightSplit = value.split(",")

        var width = 0
        var height = 0

        widthHeightSplit.forEach {
            val propertyValueSplit = it.split(MULTIPLE_VALUES_DELIMITER)
            when { //todo -> implement trim everywhere like this
                propertyValueSplit[PROPERTY] == "width" -> width = propertyValueSplit[VALUE].toInt()
                propertyValueSplit[PROPERTY].trim() == "height" -> height = propertyValueSplit[VALUE].trim().toInt()
            }
        }

        setWidthHeight(width, height)
    }

    private fun processPositionProperty(value: String) {
        Log.d("processor", "position -> $value")

        val topLeftSplit = value.split(",")

        var top = 0
        var left = 0

        topLeftSplit.forEach {
            val propertyValueSplit = it.split(MULTIPLE_VALUES_DELIMITER)
            when { //todo -> implement trim everywhere like this
                propertyValueSplit[PROPERTY] == "top" -> top = propertyValueSplit[VALUE].toInt()
                propertyValueSplit[PROPERTY].trim() == "left" -> left = propertyValueSplit[VALUE].trim().toInt()
            }
        }

        Log.d("supertest", "top -> $top, left -> $left")

        setPosition(top, left)
    }

    var width: Int? = null
    var height: Int? = null

    private fun setWidthHeight(passedWidth: Int, passedHeight: Int) {
        width = Utils(this).dpToPx(passedWidth)
        height = Utils(this).dpToPx(passedHeight)

        val params = RelativeLayout.LayoutParams(width!!, height!!)

        preview_dummy_txtview.layoutParams = params
    }

    private fun setPosition(passedTop: Int, passedLeft: Int) {
        val topMargin = Utils(this).dpToPx(passedTop)
        val leftMargin = Utils(this).dpToPx(passedLeft)

        val params = RelativeLayout.LayoutParams(width!!, height!!)
        params.topMargin = topMargin
        params.leftMargin = leftMargin

        preview_dummy_txtview.layoutParams = params
    }







//    todo -> use when creating new textview etc
    //        // Some existing RelativeLayout from your layout xml
//        val root: RelativeLayout = findViewById(R.id.preview_root_layout)
//
//        val tv = TextView(this).apply {
//            text = intent.getStringExtra("command") //"Command Screen"
//            textSize = 42f
//            gravity = Gravity.CENTER
//            typeface = typefaces[Utils.TYPEFACE_LIGHT]
//        }
    //        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
    //todo -> not adding currently to make it invisible
//        root.addView(tv, params)



//    private fun getPosition(parentViewGroup: ViewGroup, childView: View) {
//        val offsetViewBounds = Rect()
//        //returns the visible bounds
//        childView.getDrawingRect(offsetViewBounds)
//        // calculates the relative coordinates to the parent
//        parentViewGroup.offsetDescendantRectToMyCoords(childView, offsetViewBounds)
//
//        val relativeTop = Utils(this).pxToDp(offsetViewBounds.top)
//        val relativeLeft = Utils(this).pxToDp(offsetViewBounds.left)
//
//        val cw = Utils(this).pxToDp(childView.width)
//        val ch = Utils(this).pxToDp(childView.height)
//
//        logger(relativeTop, relativeLeft, cw, ch)
//    }
//
//    private fun logger(relativeTop: Int, relativeLeft: Int, cw: Int, ch: Int) {
//        Log.d("LOG-TIMBER", "TOP -> $relativeTop")
//        Log.d("LOG-TIMBER", "LEFT -> $relativeLeft")
//        Log.d("LOG-TIMBER", "WIDTH -> $cw")
//        Log.d("LOG-TIMBER", "HEIGHT -> $ch")
//    }

    /* Typefaces */
    private fun initTypefaces() {
        typefaces = arrayListOf(
            ResourcesCompat.getFont(applicationContext, R.font.helvetica_neue_thin),
            ResourcesCompat.getFont(applicationContext, R.font.helvetica_neue_light),
            ResourcesCompat.getFont(applicationContext, R.font.helvetica_neue_medium),
            ResourcesCompat.getFont(applicationContext, R.font.helvetica_neue_bold))
    }

    /* Getting Command Line-By-Line through Intent and Applying lines() function */
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun getCommandLineByLine() = intent.getStringExtra("command").lines()

    //todo - pasted from commandactivity directly
    //        /* Waiting for layout to be rendered, first */
//        val vto = command_root_relative_layout.viewTreeObserver
//        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                command_root_relative_layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
//                /* Calling getPosition() here */
////                getPosition(command_root_relative_layout, command_title_txtview)
//            }
//        })

//        setWidthHeight()

}
