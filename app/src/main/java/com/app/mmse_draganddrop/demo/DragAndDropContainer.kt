package com.app.mmse_draganddrop.demo

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.*
import com.app.draganddrop.R
import com.app.mmse_draganddrop.demo.label.Label2
import com.app.mmse_draganddrop.demo.play_audio_file.PlayAudioFile
import kotlinx.android.synthetic.main.activity_toolbox2.view.*

class DragAndDropContainer: RelativeLayout {

    constructor(context: Context?) : super(context) { setupContainer() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { setupContainer() }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupContainer() {
        inflate(context, R.layout.drag_and_drop_container, this)

        //textview_save_test_1.text = Label2(context).getState()
    }

    fun addLabelOriginal() = addView(Label2(context))
    fun addPlayAudioFileOriginal() = addView(PlayAudioFile(context))
}