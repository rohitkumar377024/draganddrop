package com.app.mmse_draganddrop.demo.label

import android.content.Context
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.app.draganddrop.R
import com.app.mmse_draganddrop.Utils
import kotlin.math.floor

class LabelUtils(private val ctx: Context) {

    //Helps in Font Weight Stuff
    fun fontWeightHelper(it: View, typeface: Int, sampleTextView: TextView, fontWeightBtns: ArrayList<Button>) {
        sampleTextView.typeface = Utils(ctx).typefaces[typeface]
        for (btn in fontWeightBtns) {
            when (it) {
                btn -> (it as Button).setTextColor(ContextCompat.getColor(ctx, R.color.colorMainYellow))
                else -> btn.setTextColor(ContextCompat.getColor(ctx, android.R.color.white))
            }
        }
    }

    //Shows change text LL, request focus for editText and shows soft keyboard
    fun changeTextDone(changeTextLL: LinearLayout, changeEditText: EditText) {
        Utils(ctx).show(changeTextLL)
        changeEditText.requestFocus()
        Utils(ctx).showSoftKeyboard(ctx)
    }

    //Handles changing of text
    fun handleChangedText(view: View, changeTextLL: LinearLayout, sampleTextView: TextView, changeEditText: EditText) {
        Utils(ctx).hide(changeTextLL)
        val existingText = sampleTextView.text
        val changedText = changeEditText.text.toString()
        //Handles Sample Text Change
        sampleTextView.text = when {
            changedText.isEmpty() -> existingText
            else -> changedText
        }
        Utils(ctx).hideSoftKeyboard(ctx, view)
    }

    //Text Size Value At Start
    fun textSizeInitialValue(textSizeEditText: EditText, sampleTextView: TextView) {
        //Initial values for EditText and Text Size of Label
        val initial = floor((((Label2.TEXT_SIZE_MAX - Label2.TEXT_SIZE_MIN) / 3) - 1).toDouble())
        textSizeEditText.setText("$initial")
        sampleTextView.textSize =  initial.toFloat()
    }

    //Handles state when Text Size Change is Done
    fun textSizeDone(it: View, textSizeSeekbar: SeekBar, textSizeEditText: EditText, seekbarLL: LinearLayout) {
        textSizeSeekbar.progress = textSizeEditText.text.toString().toInt() - Label2.TEXT_SIZE_MIN
        Utils(ctx).hideSoftKeyboard(it.context, it)
        Utils(ctx).hide(seekbarLL)
    }

    //Makes the SeekBar Work for Text Size Change
    fun configureTextSizeChangeSeekBar(textSizeSeekbar: SeekBar, sampleTextView: TextView, textSizeEditText: EditText) {
        textSizeSeekbar.max = (Label2.TEXT_SIZE_MAX - Label2.TEXT_SIZE_MIN) / Label2.TEXT_SIZE_STEP
        textSizeSeekbar.progress = floor((((Label2.TEXT_SIZE_MAX - Label2.TEXT_SIZE_MIN) / 3) - 1).toDouble()).toInt()
        textSizeSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = Label2.TEXT_SIZE_MIN + progress * Label2.TEXT_SIZE_STEP
                sampleTextView.textSize = value.toFloat()
                textSizeEditText.setText(value.toString())
            }
        })
    }

    //Hiding Properties Pane
    fun hidePropertiesPane(elementsToBeHidden: ArrayList<View>) {
        elementsToBeHidden.forEach { Utils(ctx).hide(it) }
    }

    //Showing Properties Pane
    fun showPropertiesPane(elementsToBeShown: ArrayList<View>) {
        elementsToBeShown.forEach { Utils(ctx).show(it) }
    }

}