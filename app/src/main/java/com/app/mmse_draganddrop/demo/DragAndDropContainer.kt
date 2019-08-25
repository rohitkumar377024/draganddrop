package com.app.mmse_draganddrop.demo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
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
    }

    //todo -> modifying here to handle state
    fun addLabelOriginal() {
        val l = Label2(context)
        addView(l)

        val x = l.getState()
        Log.d("xyz->recieved", "$x")

        Handler().postDelayed({
            val y = l.getState()
            Log.d("xyz->recieved", "$y")
        }, 1500)

    }

    fun addPlayAudioFileOriginal() = addView(PlayAudioFile(context))
}