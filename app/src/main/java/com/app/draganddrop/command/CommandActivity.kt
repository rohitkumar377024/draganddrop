package com.app.draganddrop.command

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.core.content.res.ResourcesCompat
import com.app.draganddrop.R
import kotlinx.android.synthetic.main.activity_command.*
import android.widget.RelativeLayout
import android.widget.TextView
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.app.draganddrop.Utils
import android.view.ViewTreeObserver.OnGlobalLayoutListener

class CommandActivity : AppCompatActivity() {

    companion object {
        //Uses '=' for Splitting
        const val PROPERTY_VALUE_DELIMITER = "="
        //Constants for Property Name and Value Identification
        const val PROPERTY = 0
        const val VALUE = 1
    }

        //ArrayList for Typefaces
        private var typefaces= arrayListOf<Typeface?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command)

        /* Initializing the ArrayList storing Typefaces */
         initTypefaces()

        /* Start Processing the Command Once the Button is Clicked */
        process_command_btn.setOnClickListener { processCommand() }

        /* Waiting for layout to be rendered, first */
        val vto = command_root_relative_layout.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                command_root_relative_layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                /* Calling getPosition() here */
                getPosition(command_root_relative_layout, process_command_btn)
            }
        })

        setPosition()
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
            }
        }
    }

    private fun processTextProperty(value: String) {
        Log.d("Commander", "Text Property -> $value")
        command_title_txtview.text = value
    }
    private fun processTextSizeProperty(value: String) {
        Log.d("Commander", "Text Size Property -> $value")
        command_title_txtview.textSize = value.toFloat()
    }
    private fun processFontWeightProperty(value: String) {
        Log.d("Commander", "Font Weight Property -> $value")
        when (value) {
            "Thin" -> command_title_txtview.typeface = typefaces[Utils.TYPEFACE_THIN]
            "Light" -> command_title_txtview.typeface = typefaces[Utils.TYPEFACE_LIGHT]
            "Medium" -> command_title_txtview.typeface = typefaces[Utils.TYPEFACE_MEDIUM]
            "Bold" -> command_title_txtview.typeface = typefaces[Utils.TYPEFACE_BOLD]
        }
    }

    /*Getting Command Input */
    private fun getCommandLineByLine() = command_activity_input_edittext.text.toString().lines()

    /* Typefaces */
    private fun initTypefaces() {
        typefaces = arrayListOf(
            ResourcesCompat.getFont(applicationContext, R.font.helvetica_neue_thin),
            ResourcesCompat.getFont(applicationContext, R.font.helvetica_neue_light),
            ResourcesCompat.getFont(applicationContext, R.font.helvetica_neue_medium),
            ResourcesCompat.getFont(applicationContext, R.font.helvetica_neue_bold))
    }

    private fun setPosition() {
        // Some existing RelativeLayout from your layout xml
        val root: RelativeLayout = findViewById(R.id.command_root_relative_layout)

        val tv = TextView(this).apply {
            text = "Random"
            textSize = 24f
            gravity = Gravity.CENTER
            typeface = typefaces[Utils.TYPEFACE_MEDIUM]
            setTextColor(Color.WHITE)
            setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.playAudioFileRecordingButton))
        }

        val width = Utils(this).dpToPx(379)
        val height = Utils(this).dpToPx(49)

        val topMargin = Utils(this).dpToPx(168 + 50 + 10)
        val leftMargin = Utils(this).dpToPx(16)

//        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        val params = RelativeLayout.LayoutParams(width, height)
        params.topMargin = topMargin
        params.leftMargin = leftMargin
//        root.addView(tv, params)
        //todo -> not adding view right now above 
    }

    private fun getPosition(parentViewGroup: ViewGroup, childView: View) {
        val offsetViewBounds = Rect()
        //returns the visible bounds
        childView.getDrawingRect(offsetViewBounds)
        // calculates the relative coordinates to the parent
        parentViewGroup.offsetDescendantRectToMyCoords(childView, offsetViewBounds)

        val relativeTop = Utils(this).pxToDp(offsetViewBounds.top)
        val relativeLeft = Utils(this).pxToDp(offsetViewBounds.left)

        val cw = Utils(this).pxToDp(childView.width)
        val ch = Utils(this).pxToDp(childView.height)

       logger(relativeTop, relativeLeft, cw, ch)
    }

    private fun logger(relativeTop: Int, relativeLeft: Int, cw: Int, ch: Int) {
        Log.d("LOG-TIMBER", "TOP -> $relativeTop")
        Log.d("LOG-TIMBER", "LEFT -> $relativeLeft")
        Log.d("LOG-TIMBER", "WIDTH -> $cw")
        Log.d("LOG-TIMBER", "HEIGHT -> $ch")
    }

}
