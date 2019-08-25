package com.app.mmse_draganddrop.demo.label

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import com.app.draganddrop.R
import com.app.mmse_draganddrop.Utils
import com.app.mmse_draganddrop.command.LabelCmd
import com.app.mmse_draganddrop.command.PositionDimensionCalculator
import kotlinx.android.synthetic.main.frame_property_layout.view.*
import kotlinx.android.synthetic.main.label_layout.view.*
import kotlinx.android.synthetic.main.label_properties_boss_layout.view.*
import kotlinx.android.synthetic.main.on_click_property_layout.view.*

class Label2 : RelativeLayout {

    companion object {
        lateinit var propertiesPaneHideArr: ArrayList<View> //This ArrayList contains all the elements which will hidden when Properties Pane is Closed
        lateinit var propertiesPaneShowArr: ArrayList<View> //This ArrayList contains all the elements which will hidden when Properties Pane is Shown
    }

    private lateinit var fontWeightBtns: ArrayList<Button> //Variable for storing Font Weight Buttons

    var fontWeightVar = "light" //todo -> default is 'light'

    constructor(context: Context?) : super(context) { setupProperties() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)  { setupProperties() }

    private fun setupProperties() {
        inflate(context, R.layout.label_layout, this)

        initPropertyHideShowArrayLists()

        //Properties Pane Initially Invisible
        LabelUtils(context).hidePropertiesPane(propertiesPaneHideArr)

        //Initializing the ArrayList first for Font Weight Buttons to Modify Later
        fontWeightBtns = arrayListOf(label_font_weight_thin, label_font_weight_light, label_font_weight_medium, label_font_weight_bold)

        //Setting Touch Listener for Text
        label_sample_textview.setOnTouchListener(LabelTouchListener(this, label_sample_textview))

        //Clicking on text shows Properties Pane
        label_sample_textview.setOnClickListener { LabelUtils(context).showPropertiesPane(propertiesPaneShowArr) }

        //Showing Text Size Layout after 'Change Text Size' Button Clicked
        label_change_text_size_main_btn.setOnClickListener { Utils(context).show(label_seekbar_ll) }

        //Setting Initial values for EditText and Text Size of Label
        LabelUtils(context).textSizeInitialValue( label_text_size_edittext, label_sample_textview)

        //Configuring the Text Size Changing SeekBar
        LabelUtils(context).configureTextSizeChangeSeekBar(label_text_size_seekbar, label_sample_textview, label_text_size_edittext)

        //Making Done Button Work for Text Size
        label_text_size_done_btn.setOnClickListener { LabelUtils(context).textSizeDone(it, label_text_size_seekbar,  label_text_size_edittext, label_seekbar_ll) }

        //Initializing the Font Weight Parameter Here
        handleFontWeight()

        //Handling Change Text Stuff
        label_change_text_main_btn.setOnClickListener { LabelUtils(context).changeTextDone(label_change_text_ll, label_text_change_edittext) }
        label_text_change_done_btn.setOnClickListener { LabelUtils(context).handleChangedText(it, label_change_text_ll, label_sample_textview, label_text_change_edittext) }

        //Clicking on close button hides Properties Pane
        label_properties_close_btn.setOnClickListener { LabelUtils(context).handleCloseBtn(it) }

        //todo -> Handling OnClick and Frame
        label_on_click_main_btn.setOnClickListener { Utils(context).show(on_click_include_layout) }
        label_frame_main_btn.setOnClickListener { Utils(context).show(frame_include_layout) }

        on_click_property_setup_btn.setOnClickListener { Utils(context).toast("OnClick -> Clicked") }
        frame_property_select_btn.setOnClickListener { Utils(context).toast("Frame -> Clicked") }
    }

    //Called from DragAndDropContainer to Get The State of the Label
    fun getState(): LabelCmd {
        val text = label_sample_textview.text.toString()
        val textSize = label_text_size_edittext.text.toString().toFloat()
        val fontWeight = fontWeightVar

        val dimensions = PositionDimensionCalculator(context).getDimensions(label_sample_textview)
        val position = PositionDimensionCalculator(context).getPosition(label_sample_textview)

        return LabelCmd(text, textSize, fontWeight, dimensions.first, dimensions.second, position.first, position.second) //last 4 -> width, height, top, left
    }

    //Setting up Font Weights
    private fun handleFontWeight() { //todo -> modifying fontWeightVar here below
        label_font_weight_thin.setOnClickListener { LabelUtils(context).fontWeightHelper(it, Utils.TYPEFACE_THIN, label_sample_textview, fontWeightBtns); fontWeightVar = "thin" }
        label_font_weight_light.setOnClickListener { LabelUtils(context).fontWeightHelper(it, Utils.TYPEFACE_LIGHT, label_sample_textview, fontWeightBtns); fontWeightVar = "light" }
        label_font_weight_medium.setOnClickListener { LabelUtils(context).fontWeightHelper(it, Utils.TYPEFACE_MEDIUM, label_sample_textview, fontWeightBtns); fontWeightVar = "medium" }
        label_font_weight_bold.setOnClickListener { LabelUtils(context).fontWeightHelper(it, Utils.TYPEFACE_BOLD, label_sample_textview, fontWeightBtns); fontWeightVar = "bold" }
    }

    private fun initPropertyHideShowArrayLists() {
        propertiesPaneHideArr = arrayListOf(label_seekbar_ll, label_change_text_ll, label_properties_title_ll, label_bottom_buttons_ll, label_font_weight_ll, on_click_include_layout, frame_include_layout)
        propertiesPaneShowArr = arrayListOf(label_properties_title_ll, label_bottom_buttons_ll, label_font_weight_ll)
    }

}