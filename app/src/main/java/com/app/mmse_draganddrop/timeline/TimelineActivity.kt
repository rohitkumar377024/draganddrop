package com.app.mmse_draganddrop.timeline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.app.draganddrop.R
import com.app.mmse_draganddrop.Utils
import kotlinx.android.synthetic.main.activity_timeline.*

class TimelineActivity : AppCompatActivity() {

    companion object {
        const val FRAMES_DEFAULT = 5 //Default Number for Frames at Start
    }

    var frameCounter = FRAMES_DEFAULT  //At start, the frameCounter will be equal to default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        /* Adding the default frames at start */
        addDefaultFrames()
        /* Adds frame by pre-incrementing frame counter */
        add_frame_btn.setOnClickListener { addFrame((++frameCounter).toString()) }

        add_textview_btn.setOnClickListener {
            Utils(this).toast("TextView Added")
        }
    }

    /* Handles clicking of a specific frame */
    private fun processFrameClick(frameNumber: Int, view: View) {
        Utils(this).toast("Frame $frameNumber")
        showFrameSelected(view as TextView)
    }

    /* Logic for Adding a Frame */
    private fun addFrame(message: String) {
        val a = TextView(this).apply {
            text = message //Assigning the passed value as text for Frame
            textSize = 35f
            setPadding(15, 15, 15, 15)
            setTextColor(ContextCompat.getColor(applicationContext, R.color.colorMainYellow))
            typeface = Utils(applicationContext).typefaces[Utils.TYPEFACE_THIN]
            setOnClickListener { processFrameClick(message.toInt(), it) }
        }
        timeline_dynamic_ll.addView(a)
    }

    /* Highlights the frame selected */
    private fun showFrameSelected(textView: TextView) {
        textView.typeface = Utils(this).typefaces[Utils.TYPEFACE_MEDIUM]
    }

    /* Adds the default number of frames */
    private fun addDefaultFrames() {
        for (i in 1..FRAMES_DEFAULT) {
            addFrame(i.toString())
        }
    }

}
