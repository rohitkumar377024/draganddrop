package com.app.mmse_draganddrop.demo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.widget.*
import com.app.draganddrop.R
import com.app.mmse_draganddrop.command.LabelCmd
import com.app.mmse_draganddrop.demo.label.Label2
import com.app.mmse_draganddrop.demo.play_audio_file.PlayAudioFile
import kotlinx.android.synthetic.main.drag_and_drop_container.view.*

class DragAndDropContainer: RelativeLayout {

    companion object {
        //This 'slight' amount of delay allows to avoid the '0' Value Bug that occurs due to displaying values before rendering of the layout
        const val DELAY_VALUE: Long = 500
    }

    //Stores the current frame state
    private var frameState = arrayListOf<Any>()

    private var frameDummy = arrayListOf<Any>()

    constructor(context: Context?) : super(context) { setupContainer() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { setupContainer() }

    @SuppressLint("ClickableViewAccessibility") private fun setupContainer() {
        inflate(context, R.layout.drag_and_drop_container, this)
        get_state_dummy_button.setOnClickListener { showMeStateOfIndividualFrame() }
    }

    //Shows the 'Individual' Frame State
    private fun showMeStateOfIndividualFrame() {
        Log.d("frameState", "$frameState")


        frameDummy.forEach {
            Log.d("check3", "${(it as Label2).getState()}")
        }

        //Log.d("check3", "${(frameDummy[0] as Label2).getState()}")
    }

    fun addLabelOriginal() {
        val newLabelCreated = Label2(context)
        addView(newLabelCreated)

        frameDummy.add(newLabelCreated)

//        Handler().postDelayed({
//            val newLabelState = newLabelCreated.getState()
//            frameState.add(newLabelState)
//        }, DELAY_VALUE)
    }

    fun addPlayAudioFileOriginal() = addView(PlayAudioFile(context))
}