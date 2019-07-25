package com.app.draganddrop.toolbox2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.draganddrop.R
import com.app.draganddrop.demo.tools.PlayAudioFile
import kotlinx.android.synthetic.main.activity_toolbox2.*

class Toolbox2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbox2)

        tool_box_label.setOnClickListener { drag_and_drop_container.addLabelOriginal() } //Label
        tool_box_play_audio_file.setOnClickListener { drag_and_drop_container.addPlayAudioFileOriginal() } //Play Audio
    }

    //Handling Activity Results for Select Audio Clip --> 'Play Audio File' Tool
    //Why we are handling here? -> Because it requires an Activity to start for result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        PlayAudioFile(this).onActivityResult(requestCode, resultCode, data)
    }

}
