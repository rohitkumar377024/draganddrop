package com.app.draganddrop.demo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.*
import com.app.draganddrop.R
import com.app.draganddrop.demo.tools.Label2
import com.app.draganddrop.demo.tools.PlayAudioFile

class DragAndDropContainer: RelativeLayout {

    constructor(context: Context?) : super(context) { setupContainer() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { setupContainer() }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupContainer() {
        inflate(context, R.layout.drag_and_drop_container, this)
    }

    fun addLabelOriginal() = addView(Label2(context))
    fun addPlayAudioFileOriginal() = addView(PlayAudioFile(context))
}