package com.app.draganddrop.demo.label

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.app.draganddrop.R
import com.app.draganddrop.Utils
import kotlinx.android.synthetic.main.frame_property_layout.view.*
import kotlinx.android.synthetic.main.label_layout.view.*
import kotlinx.android.synthetic.main.label_properties_boss_layout.view.*
import kotlinx.android.synthetic.main.on_click_property_layout.view.*
import kotlin.math.floor

class Label2 : RelativeLayout {

    //ArrayList for Typefaces
    private val typefaces = arrayListOf(
        ResourcesCompat.getFont(context, R.font.helvetica_neue_thin),
        ResourcesCompat.getFont(context, R.font.helvetica_neue_light),
        ResourcesCompat.getFont(context, R.font.helvetica_neue_medium),
        ResourcesCompat.getFont(context, R.font.helvetica_neue_bold))

    companion object {
        //Text Size Changing SeekBar Values
        const val TEXT_SIZE_STEP = 1
        const val TEXT_SIZE_MIN = 2
        const val TEXT_SIZE_MAX = 96
    }

    //Variable for storing Font Weight Buttons
    private lateinit var fontWeightBtns: ArrayList<Button>

    //State Handling
    private val stateTest = "stateTest -> Text"

    fun getState(): String { return stateTest }

    //text
    //text size
    //text style
    //location of textview

    constructor(context: Context?) : super(context) { setupProperties() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)  { setupProperties() }

    private fun setupProperties() {
        inflate(context, R.layout.label_layout, this)

        //Properties Pane Initially Invisible
        hidePropertiesPane()

        //Initializing the ArrayList first for Font Weight Buttons to Modify Later
        fontWeightBtns = arrayListOf(label_font_weight_thin,
            label_font_weight_light, label_font_weight_medium, label_font_weight_bold)

        //Setting Touch Listener for Text
        label_sample_textview.setOnTouchListener(LabelTouchListener(this, label_sample_textview))

        //Clicking on text shows Properties Pane
        label_sample_textview.setOnClickListener { showPropertiesPane() }

        //Clicking on close button hides Properties Pane
        label_properties_close_btn.setOnClickListener { hidePropertiesPane(); Utils(
            context
        ).hideSoftKeyboard(context, it) }

        //Showing Text Size Layout after 'Change Text Size' Button Clicked
        label_change_text_size_main_btn.setOnClickListener { Utils(context).show(label_seekbar_ll) }

        //Setting Initial values for EditText and Text Size of Label
        textSizeInitialValue()

        //Configuring the Text Size Changing SeekBar
        configureTextSizeChangeSeekBar()

        //Making Done Button Work for Text Size
        label_text_size_done_btn.setOnClickListener { textSizeDone(it) }

        //Initializing the Font Weight Parameter Here
        handleFontWeight()

        //Handling Change Text Stuff
        label_change_text_main_btn.setOnClickListener { changeTextDone() }
        label_text_change_done_btn.setOnClickListener { handleChangedText(it) }

        //todo -> Handling OnClick and Frame
        label_on_click_main_btn.setOnClickListener { Utils(context).show(on_click_include_layout) }
        label_frame_main_btn.setOnClickListener { Utils(context).show(frame_include_layout) }

        on_click_property_setup_btn.setOnClickListener {
//            Toast.makeText(context, "OnClick -> Clicked", Toast.LENGTH_SHORT).show()
            Utils(context).toast("OnClick -> Clicked")
        }
        frame_property_select_btn.setOnClickListener {
//            Toast.makeText(context, "Frame -> Clicked", Toast.LENGTH_SHORT).show()
            Utils(context).toast("Frame -> Clicked")
        }
    }

    //Setting up Font Weights
    private fun handleFontWeight() {
        label_font_weight_thin.setOnClickListener { fontWeightHelper(it, Utils.TYPEFACE_THIN) }
        label_font_weight_light.setOnClickListener { fontWeightHelper(it, Utils.TYPEFACE_LIGHT) }
        label_font_weight_medium.setOnClickListener { fontWeightHelper(it, Utils.TYPEFACE_MEDIUM) }
        label_font_weight_bold.setOnClickListener { fontWeightHelper(it, Utils.TYPEFACE_BOLD) }
    }

    //Helps in Font Weight Stuff
    private fun fontWeightHelper(it: View, typeface: Int) {
        label_sample_textview.typeface = typefaces[typeface]
        for (btn in fontWeightBtns) {
            when (it) {
                btn -> {
                    val itsAButton = it as Button
                    itsAButton.setTextColor(ContextCompat.getColor(context, R.color.colorPropertiesYellow))
                }
                else -> btn.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            }
        }
    }

    //Shows change text LL, request focus for editText and shows soft keyboard
    private fun changeTextDone() {
        Utils(context).show(label_change_text_ll)
        label_text_change_edittext.requestFocus()
        Utils(context).showSoftKeyboard(context)
    }

    //Handles changing of text
    private fun handleChangedText(view: View) {
        Utils(context).hide(label_change_text_ll)
        val existingText = label_sample_textview.text
        val changedText = label_text_change_edittext.text.toString()
        //Handles Sample Text Change
        label_sample_textview.text = when {
            changedText.isEmpty() -> existingText
            else -> changedText
        }
        Utils(context).hideSoftKeyboard(context, view)
    }

    //Text Size Value At Start
    private fun textSizeInitialValue() {
        //Initial values for EditText and Text Size of Label
        val initial = floor((((TEXT_SIZE_MAX - TEXT_SIZE_MIN) / 3) - 1).toDouble())
        label_text_size_edittext.setText("$initial")
        label_sample_textview.textSize =  initial.toFloat()
    }

    //Makes the Seekbar Work for Text Size Change
    private fun configureTextSizeChangeSeekBar() {
        label_text_size_seekbar.max = (TEXT_SIZE_MAX - TEXT_SIZE_MIN) / TEXT_SIZE_STEP
        label_text_size_seekbar.progress = floor((((TEXT_SIZE_MAX - TEXT_SIZE_MIN) / 3) - 1).toDouble()).toInt()
        label_text_size_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = TEXT_SIZE_MIN + progress * TEXT_SIZE_STEP
                label_sample_textview.textSize = value.toFloat()
                label_text_size_edittext.setText(value.toString())
            }
        })
    }

    //Handles state when Text Size Change is done
    private fun textSizeDone(it: View) {
        label_text_size_seekbar.progress = label_text_size_edittext.text.toString().toInt() - TEXT_SIZE_MIN
        Utils(context).hideSoftKeyboard(it.context, it)
        Utils(context).hide(label_seekbar_ll)
    }

    //Hide the properties pane -> All LL
    private fun hidePropertiesPane() {
        Utils(context).hide(label_seekbar_ll)
        Utils(context).hide(label_change_text_ll)
        Utils(context).hide(label_properties_title_ll)
        Utils(context).hide(label_bottom_buttons_ll)
        Utils(context).hide(label_font_weight_ll)
        Utils(context).hide(on_click_include_layout)
        Utils(context).hide(frame_include_layout)
    }

    //Show the properties pane - All LL except SeekBar and ChangeText one
    //Not private because it is called by OnTouchListener's Click
    fun showPropertiesPane() {
        Utils(context).show(label_properties_title_ll)
        Utils(context).show(label_bottom_buttons_ll)
        Utils(context).show(label_font_weight_ll)
    }

}