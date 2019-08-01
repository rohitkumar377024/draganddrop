package com.app.draganddrop.extras

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.app.draganddrop.R
import kotlin.math.floor

class Label : RelativeLayout/* , View.OnTouchListener */ {

//    var width2 = 0//v?.layoutParams?.width
//    var height2 = 0//v?.layoutParams?.height

    //Text Size Changing SeekBar Values
    private val step = 1
    private val min = 2
    private val max = 96

//    //Both help in getting the drag physics precise
//    private var dX: Float? = null //DeltaX for detecting change in X
//    private var dY: Float? = null //DeltaY for detecting change in Y
//
//    private var lastAction: Int? = null //Tracks last action for differentiating b/w Touch and Click

    private lateinit var propertiesTitleLL: LinearLayout //Top LL shows 'Label2' and 'X'
    private lateinit var changeTextSizeSeekBarLL: LinearLayout //LL showing options to change text size
    private lateinit var changeTextLL: LinearLayout //LL showing options to change text
    private lateinit var labelBottomLL: LinearLayout //LL showing main buttons for changing text and size
    private lateinit var fontWeightLL: LinearLayout //LL showing font weights for changing how text looks
    private lateinit var sample: TextView //Actual TextView
    private lateinit var changeTextBtn: Button //Main button for changing text
    private lateinit var changeTextSizeBtn: Button //Main button for changing text size
    private lateinit var textSizeSeekBar: SeekBar //SeekBar to change the text size
    private lateinit var propertiesCloseBtn: ImageView //'X' Button that closes the property pane
    private lateinit var changeTextSizeDoneBtn: TextView //Done button for changing text size
    private lateinit var changeTextEditText: EditText //EditText that takes value for changing the existing text
    private lateinit var changeTextDoneBtn: TextView //Done button for changing text
    private lateinit var textSizeEditText: EditText //EditText to see and change actual Text Size
    private lateinit var fontWeightThinBtn: Button //Sets font weight to Thin
    private lateinit var fontWeightLightBtn: Button //Sets font weight to Light
    private lateinit var fontWeightMediumBtn: Button //Sets font weight to Medium
    private lateinit var fontWeightBoldBtn: Button //Sets font weight to Bold

    private lateinit var fontWeightBtns: ArrayList<Button>

    private val typefaces = arrayListOf(
        ResourcesCompat.getFont(context, R.font.helvetica_neue_thin),
        ResourcesCompat.getFont(context, R.font.helvetica_neue_light),
        ResourcesCompat.getFont(context, R.font.helvetica_neue_medium),
        ResourcesCompat.getFont(context, R.font.helvetica_neue_bold))

    companion object {
        private const val TYPEFACE_THIN = 0
        private const val TYPEFACE_LIGHT = 1
        private const val TYPEFACE_MEDIUM = 2
        private const val TYPEFACE_BOLD = 3
    }

    //Inflating Label Layout in Constructors
    constructor(context: Context?) : super(context) { setupView() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { setupView() }

    //Code for Inflation
    private fun setupView() {
        inflate(context, R.layout.label_layout, this)
    }

    //Called once Layout Inflation is over
    @SuppressLint("ClickableViewAccessibility")
    override fun onFinishInflate() {
        super.onFinishInflate()

        Toast.makeText(context, "Inflate Finish", Toast.LENGTH_SHORT).show()
//        findViewByIds() // --> Connecting IDs with Views
//        configureTextSizeChangeSeekBar() // --> Configures the seekBar for changing text size
//        showPropertiesPane() // --> Hide propertiesPane At Start //todo -- change to hide
//        handlePropertiesCloseBtn() // --> Handles the closing of property pane
//        hide(changeTextSizeSeekBarLL)  // --> Initially textSizeSeekBar gone
//
//        //todo uncomment below line once needed
//        // sample.setOnTouchListener(LabelTouchListener(this, sample)) // --> adding touch listener here
//        // /sample.setOnClickListener { Toast.makeText(context, "Sampler", Toast.LENGTH_SHORT).show() }
//
//        //Handling Change Text Size Stuff
//        changeTextSizeBtn.setOnClickListener { show(changeTextSizeSeekBarLL) } // --> Show textSizeSeekBar
//
//        //Handling Change Text Stuff
//        changeTextBtn.setOnClickListener { handleChangeTextBtn() }
//        changeTextDoneBtn.setOnClickListener { handleChangedText(it) }
//        //Handling Font Weight
//        handleFontWeight()
//
//        //Initializing font weight buttons
//        fontWeightBtns = arrayListOf(fontWeightThinBtn, fontWeightLightBtn, fontWeightMediumBtn, fontWeightBoldBtn)
//
//        //Initial values for EditText and Text Size of Label
//        val initial = floor((((TEXT_SIZE_MAX - TEXT_SIZE_MIN) / 3) - 1).toDouble())
//        textSizeEditText.setText("$initial")
//        sample.textSize =  initial.toFloat()
//
//        changeTextSizeDoneBtn.setOnClickListener {
//            textSizeSeekBar.progress = textSizeEditText.text.toString().toInt() - TEXT_SIZE_MIN
//            hideSoftKeyboard(it)
//            hide(changeTextSizeSeekBarLL)
//        }
    }

    //Handles Overall Font Weight
    private fun handleFontWeight() {
        fontWeightThinBtn.setOnClickListener { fontWeightHelper(it,
            TYPEFACE_THIN
        ) }
        fontWeightLightBtn.setOnClickListener { fontWeightHelper(it,
            TYPEFACE_LIGHT
        ) }
        fontWeightMediumBtn.setOnClickListener { fontWeightHelper(it,
            TYPEFACE_MEDIUM
        ) }
        fontWeightBoldBtn.setOnClickListener { fontWeightHelper(it,
            TYPEFACE_BOLD
        ) }
    }

    //Helps in Font Weight Stuff
    private fun fontWeightHelper(it: View, typeface: Int) {
        sample.typeface = typefaces[typeface]
        for (btn in fontWeightBtns) {
            if (it == btn) {
                val a = it as Button
                a.setTextColor(ContextCompat.getColor(context,
                    R.color.colorPropertiesYellow
                ))
            } else {
                btn.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            }
        }
    }

    private fun findViewByIds() {
        //Initializing here so its not null
        propertiesTitleLL = findViewById(R.id.label_properties_title_ll)
        changeTextSizeSeekBarLL = findViewById(R.id.label_seekbar_ll)
        changeTextLL = findViewById(R.id.label_change_text_ll)
        labelBottomLL = findViewById(R.id.label_bottom_buttons_ll)
        fontWeightLL = findViewById(R.id.label_font_weight_ll)
        changeTextBtn = findViewById(R.id.label_change_text_main_btn)
        sample = findViewById(R.id.label_sample_textview)
        textSizeSeekBar = findViewById(R.id.label_text_size_seekbar)
        changeTextSizeBtn = findViewById(R.id.label_change_text_size_main_btn)
        changeTextSizeDoneBtn = findViewById(R.id.label_text_size_done_btn)
        propertiesCloseBtn = findViewById(R.id.label_properties_close_btn)
        changeTextEditText = findViewById(R.id.label_text_change_edittext)
        changeTextDoneBtn = findViewById(R.id.label_text_change_done_btn)
        textSizeEditText = findViewById(R.id.label_text_size_edittext)
        fontWeightThinBtn = findViewById(R.id.label_font_weight_thin)
        fontWeightLightBtn = findViewById(R.id.label_font_weight_light)
        fontWeightMediumBtn = findViewById(R.id.label_font_weight_medium)
        fontWeightBoldBtn = findViewById(R.id.label_font_weight_bold)
    }

    //Shows change text LL, request focus for editText and shows soft keyboard
    private fun handleChangeTextBtn() {
        show(changeTextLL)
        changeTextEditText.requestFocus()
        showSoftKeyboard()
    }

    //Handles changing of text
    private fun handleChangedText(view: View) {
        hide(changeTextLL)
        val existingText = sample.text
        val changedText = changeTextEditText.text.toString()
        //Handles Sample Text Change
        sample.text = when {
            changedText.isEmpty() -> existingText
            else -> changedText
        }
        hideSoftKeyboard(view)
    }

    //todo -> transferred
    //Handles the TEXT_SIZE_MIN, TEXT_SIZE_MAX and TEXT_SIZE_STEP for seekBar changing text size
    private fun configureTextSizeChangeSeekBar() {
        textSizeSeekBar.max = (max - min) / step
        textSizeSeekBar.progress = floor((((max - min) / 3) - 1).toDouble()).toInt()
        textSizeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = min + progress * step

                sample.textSize = value.toFloat()
                textSizeEditText.setText(value.toString())
            }
        })
    }

    //Handles 'X' Button
    private fun handlePropertiesCloseBtn() {
        propertiesCloseBtn.setOnClickListener {
            hidePropertiesPane()  //Hide propertiesPane When 'X' Button Clicked [Close Button]
            hideSoftKeyboard(it) //Closes Soft Keyboard if user is changing text and suddenly presses 'X'
        }
    }

    //Hide the properties pane -> All LL
    private fun hidePropertiesPane() {
        hide(changeTextSizeSeekBarLL)
        hide(changeTextLL); hide(propertiesTitleLL); hide(labelBottomLL); hide(fontWeightLL)
    }

    //Show the properties pane - All LL except SeekBar and ChangeText one
    fun showPropertiesPane() {
        show(propertiesTitleLL); show(labelBottomLL); show(fontWeightLL)
    }

    private fun hide(view: View) { view.visibility = View.GONE } // --> Hides a view
    private fun show(view: View) { view.visibility = View.VISIBLE } // --> Shows a view

    //Hides the soft keyboard
    private fun hideSoftKeyboard(view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //Shows the soft keyboard
    private fun showSoftKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

//    override fun onSizeChanged(xNew: Int, yNew: Int, xOld: Int, yOld: Int) {
//        super.onSizeChanged(xNew, yNew, xOld, yOld)
//        width2 = xNew
//        height2 = yNew
//    }

//    //Handling Drag and Drop Operation --> Smooth and Precise
//    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//
//        val width = v?.layoutParams?.width
//        val height = v?.layoutParams?.height
//
//        Log.d("texter->width", "$width")
//        Log.d("texter->height", "$height")
//
//        when (event?.action) {
//            MotionEvent.ACTION_DOWN -> {
//                lastAction = MotionEvent.ACTION_DOWN
//                dX = v?.x?.minus(event.rawX) //actual x - raw x [difference between cursor and actual drag]
//                dY = v?.y?.minus(event.rawY) //actual x - raw x [difference between cursor and actual drag]
//            }
//            MotionEvent.ACTION_MOVE -> {
//                lastAction = MotionEvent.ACTION_MOVE  //v?.x = event.rawX + dX!! //v?.y = event.rawY + dY!!
//
//
//            }
//            MotionEvent.ACTION_UP ->
//                if (lastAction == MotionEvent.ACTION_DOWN) { //Which means it is --> Clicked
//                    showPropertiesPane() //Show Label2 Pane When Clicked
//                }
//        }
//        return true
//    }

    //Animation stuff -->

//     val titleLLHeight = propertiesTitleLL.height.toFloat()
//            val labelBottomLLHeight = labelBottomLL.height.toFloat()
//            val changeTextLLHeight = changeTextLL.height.toFloat()
//            val seekBarLLHeight = changeTextSizeSeekBarLL.height.toFloat()
//
//            changeTextLL.animate()
//                .translationY(-changeTextLLHeight)
//                .alpha(1f)
//                .setListener(null)

}
