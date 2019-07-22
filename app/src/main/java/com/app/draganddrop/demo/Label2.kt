package com.app.draganddrop.demo

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.app.draganddrop.KeyboardUtils
import com.app.draganddrop.Label
import com.app.draganddrop.R
import com.app.draganddrop.VisibilityUtils
import com.app.draganddrop.first.OnDragTouchListener
import kotlinx.android.synthetic.main.label_layout.view.*
import kotlinx.android.synthetic.main.properties_boss_layout.view.*
import kotlin.math.floor

class Label2 : RelativeLayout {

    //Text Size Changing SeekBar Values
    private val step = 1
    private val min = 2
    private val max = 96

    private val typefaces = arrayListOf(
        ResourcesCompat.getFont(context, R.font.helvetica_neue_thin),
        ResourcesCompat.getFont(context, R.font.helvetica_neue_light),
        ResourcesCompat.getFont(context, R.font.helvetica_neue_medium),
        ResourcesCompat.getFont(context, R.font.helvetica_neue_bold))

    companion object {
        const val TYPEFACE_THIN = 0
        const val TYPEFACE_LIGHT = 1
        const val TYPEFACE_MEDIUM = 2
        const val TYPEFACE_BOLD = 3
    }

    private lateinit var fontWeightBtns: ArrayList<Button>

    constructor(context: Context?) : super(context) { setupProperties() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)  { setupProperties() }

    private fun setupProperties() {
        inflate(context, R.layout.label_layout, this)

        //Properties Pane Initially Invisible
        hidePropertiesPane()

        //Setting Touch Listener for Text
        label_sample_textview.setOnTouchListener(OnDragTouchListener(this, label_sample_textview))

        //Clicking on text shows Properties Pane
        label_sample_textview.setOnClickListener { showPropertiesPane() }

        //Clicking on close button hides Properties Pane
        label_properties_close_btn.setOnClickListener { hidePropertiesPane(); KeyboardUtils().hideSoftKeyboard(context, it) }

        //Showing Text Size Layout after 'Change Text Size' Button Clicked
        label_change_text_size_main_btn.setOnClickListener { VisibilityUtils().show(label_seekbar_ll) }

        //Setting Initial values for EditText and Text Size of Label
        textSizeInitialValue()

        //Configuring the Text Size Changing SeekBar
        configureTextSizeChangeSeekBar()

        //Making Done Button Work for Text Size
        label_text_size_done_btn.setOnClickListener { textSizeDone(it) }

        fontWeightBtns = arrayListOf(label_font_weight_thin,
            label_font_weight_light, label_font_weight_medium, label_font_weight_bold)

        handleFontWeight()

        //Handling Change Text Stuff
        label_change_text_main_btn.setOnClickListener { handleChangeTextBtn() }
        label_text_change_done_btn.setOnClickListener { handleChangedText(it) }
    }

    private fun handleFontWeight() {
        label_font_weight_thin.setOnClickListener { fontWeightHelper(it, TYPEFACE_THIN) }
        label_font_weight_light.setOnClickListener { fontWeightHelper(it, TYPEFACE_LIGHT) }
        label_font_weight_medium.setOnClickListener { fontWeightHelper(it, TYPEFACE_MEDIUM) }
        label_font_weight_bold.setOnClickListener { fontWeightHelper(it, TYPEFACE_BOLD) }
    }

    //Helps in Font Weight Stuff
    private fun fontWeightHelper(it: View, typeface: Int) {
        label_sample_textview.typeface = typefaces[typeface]
        for (btn in fontWeightBtns) {
            if (it == btn) {
                val a = it as Button
                a.setTextColor(ContextCompat.getColor(context, R.color.colorPropertiesYellow))
            } else {
                btn.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            }
        }
    }







    //Shows change text LL, request focus for editText and shows soft keyboard
    private fun handleChangeTextBtn() {
        VisibilityUtils().show(label_change_text_ll)
        label_text_change_edittext.requestFocus()
        KeyboardUtils().showSoftKeyboard(context)
    }

    //Handles changing of text
    private fun handleChangedText(view: View) {
        VisibilityUtils().hide(label_change_text_ll)
        val existingText = label_sample_textview.text
        val changedText = label_text_change_edittext.text.toString()
        //Handles Sample Text Change
        label_sample_textview.text = when {
            changedText.isEmpty() -> existingText
            else -> changedText
        }
        KeyboardUtils().hideSoftKeyboard(context, view)
    }








    private fun textSizeInitialValue() {
        //Initial values for EditText and Text Size of Label
        val initial = floor((((max - min) / 3) - 1).toDouble())
        label_text_size_edittext.setText("$initial")
        label_sample_textview.textSize =  initial.toFloat()
    }

    private fun configureTextSizeChangeSeekBar() {
        label_text_size_seekbar.max = (max - min) / step
        label_text_size_seekbar.progress = floor((((max - min) / 3) - 1).toDouble()).toInt()
        label_text_size_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = min + progress * step
                label_sample_textview.textSize = value.toFloat()
                label_text_size_edittext.setText(value.toString())
            }
        })
    }

    private fun textSizeDone(it: View) {
        label_text_size_seekbar.progress = label_text_size_edittext.text.toString().toInt() - min
        KeyboardUtils().hideSoftKeyboard(it.context, it)
        VisibilityUtils().hide(label_seekbar_ll)
    }






    //Hide the properties pane -> All LL
    fun hidePropertiesPane() {
        VisibilityUtils().hide(label_seekbar_ll)
        VisibilityUtils().hide(label_change_text_ll)
        VisibilityUtils().hide(label_properties_title_ll)
        VisibilityUtils().hide(label_bottom_buttons_ll)
        VisibilityUtils().hide(label_font_weight_ll)
    }

    //Show the properties pane - All LL except SeekBar and ChangeText one
    fun showPropertiesPane() {
        VisibilityUtils().show(label_properties_title_ll)
        VisibilityUtils().show(label_bottom_buttons_ll)
        VisibilityUtils().show(label_font_weight_ll)
    }

}