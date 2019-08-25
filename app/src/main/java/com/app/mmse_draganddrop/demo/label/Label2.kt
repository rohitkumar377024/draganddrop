package com.app.mmse_draganddrop.demo.label

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
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
import android.view.ViewTreeObserver.OnGlobalLayoutListener

class Label2 : RelativeLayout {

    companion object {
        //Text Size Changing SeekBar Values
        const val TEXT_SIZE_STEP = 1
        const val TEXT_SIZE_MIN = 2
        const val TEXT_SIZE_MAX = 96

        lateinit var propertiesPaneHideArr: ArrayList<View> //This ArrayList contains all the elements which will hidden when Properties Pane is Closed
        lateinit var propertiesPaneShowArr: ArrayList<View> //This ArrayList contains all the elements which will hidden when Properties Pane is Shown
    }

    //Variable for storing Font Weight Buttons
    private lateinit var fontWeightBtns: ArrayList<Button>

    var fontWeightVar = "light" //todo -> default is 'light'

    var text: String = "123"
    var textSize: Float = 123f
    var fontWeight: String = fontWeightVar

    lateinit var dimensions: Pair<Int, Int>
    lateinit var position: Pair<Int, Int>

    var width2: Int = 123
    var height2: Int = 123
    var top2: Int = 123
    var left2: Int= 123

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
        label_properties_close_btn.setOnClickListener { handleCloseBtn(it) }

        //todo -> Handling OnClick and Frame
        label_on_click_main_btn.setOnClickListener { Utils(context).show(on_click_include_layout) }
        label_frame_main_btn.setOnClickListener { Utils(context).show(frame_include_layout) }

        on_click_property_setup_btn.setOnClickListener { Utils(context).toast("OnClick -> Clicked") }
        frame_property_select_btn.setOnClickListener { Utils(context).toast("Frame -> Clicked") }

        text = label_sample_textview.text.toString()
        textSize = label_text_size_edittext.text.toString().toFloat()
        fontWeight = fontWeightVar

//        val vto = label_sample_textview.viewTreeObserver
//        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                dimensions = PositionDimensionCalculator(context).getDimensions(label_sample_textview)
//                position = PositionDimensionCalculator(context).getPosition2(label_sample_textview)
//
//                width2 = dimensions.first
//                height2 = dimensions.second
//                top2 = position.first
//                left2 = position.second
//
//                Log.d("xyz->inside", "width -> $width2, height -> $height2, top -> $top2, left -> $left2")
//                label_sample_textview.viewTreeObserver.removeOnGlobalLayoutListener(this)
//            }
//        })

//        val vto = label_sample_textview.viewTreeObserver
//        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
//            override fun onGlobalLayout() {

//                label_sample_textview.viewTreeObserver.removeOnGlobalLayoutListener(this)
//            }
//        })
    }

    //todo -> now returning state of LabelCmd Type from here
    fun getState(): LabelCmd {
        //Log.d("xyz->outside", "width -> $width2, height -> $height2, top -> $top2, left -> $left2")

        val widthHeightTopLeft = getPosDimen()
        val finalWidth = widthHeightTopLeft[0]
        val finalHeight = widthHeightTopLeft[1]
        val finalTop = widthHeightTopLeft[2]
        val finalLeft = widthHeightTopLeft[3]

        //val x = LabelCmd(text, textSize, fontWeight, width2, height2, top2, left2)
        val x = LabelCmd(text, textSize, fontWeight, finalWidth, finalHeight, finalTop, finalLeft)
        Log.d("xyz->sent", "$x")
        return x
    }

    fun getPosDimen(): ArrayList<Int> {
        Handler().postDelayed({
            dimensions = PositionDimensionCalculator(context).getDimensions(label_sample_textview)
            position = PositionDimensionCalculator(context).getPosition2(label_sample_textview)

            width2 = dimensions.first
            height2 = dimensions.second
            top2 = position.first
            left2 = position.second

            //Log.d("xyz->inside", "width -> $width2, height -> $height2, top -> $top2, left -> $left2")
        }, 500)

        return arrayListOf(width2, height2, top2, left2)
    }



















































    //Handles Close Button
    private fun handleCloseBtn(it: View) {
        LabelUtils(context).hidePropertiesPane(propertiesPaneHideArr)
        Utils(context).hideSoftKeyboard(context, it)
    }

    //Setting up Font Weights
    private fun handleFontWeight() { //todo -> modifying fontWeightVar here below
        label_font_weight_thin.setOnClickListener { LabelUtils(context).fontWeightHelper(it,
            Utils.TYPEFACE_THIN, label_sample_textview, fontWeightBtns); fontWeightVar = "thin" }
        label_font_weight_light.setOnClickListener { LabelUtils(context).fontWeightHelper(it,
                Utils.TYPEFACE_LIGHT, label_sample_textview, fontWeightBtns); fontWeightVar = "light" }
        label_font_weight_medium.setOnClickListener { LabelUtils(context).fontWeightHelper(it,
            Utils.TYPEFACE_MEDIUM, label_sample_textview, fontWeightBtns); fontWeightVar = "medium" }
        label_font_weight_bold.setOnClickListener { LabelUtils(context).fontWeightHelper(it,
            Utils.TYPEFACE_BOLD, label_sample_textview, fontWeightBtns); fontWeightVar = "bold" }
    }

    private fun initPropertyHideShowArrayLists() {
        propertiesPaneHideArr = arrayListOf(label_seekbar_ll, label_change_text_ll, label_properties_title_ll,
            label_bottom_buttons_ll, label_font_weight_ll, on_click_include_layout, frame_include_layout)
        propertiesPaneShowArr = arrayListOf(label_properties_title_ll, label_bottom_buttons_ll, label_font_weight_ll)
    }

}