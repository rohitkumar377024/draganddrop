package com.app.mmse_draganddrop.toolbox2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_toolbox2.*
import com.app.draganddrop.R
import com.app.mmse_draganddrop.command.CommandActivity
import com.app.mmse_draganddrop.command.LabelCmd
import com.app.mmse_draganddrop.command.PositionDimensionCalculator
import com.app.mmse_draganddrop.extras.Utils
import com.app.mmse_draganddrop.timeline.TimelineActivity

class Toolbox2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbox2)

        toolbox_master_constraint_layout.viewTreeObserver.addOnGlobalLayoutListener { calculateToolboxDimensions() } //Toolbox Dimensions

        tool_box_label.setOnClickListener { drag_and_drop_container.addLabelOriginal() } //Label
        tool_box_play_audio_file.setOnClickListener { drag_and_drop_container.addPlayAudioFileOriginal() } //Play Audio
    }

    /* Calculating the Dimensions of Toolbox in Dp */
    private fun calculateToolboxDimensions() {
        val toolboxDimensions = PositionDimensionCalculator(this).getDimensions(toolbox_scrollview_main)
        val toolboxWidth = toolboxDimensions.first
        val toolboxHeight = toolboxDimensions.second

        Log.d("toolbox3", "width -> $toolboxWidth, height -> $toolboxHeight")
    }

    //Creating Menu to Go To CmdUtils Activity
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    //Handling Menu Item Clicks
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_item_go_to_cmd -> startActivity(Intent(this, CommandActivity::class.java))
            R.id.menu_go_to_timeline_screen -> { goToTimelineScreen() }
            //todo -> starting import/export process from here menu items clicks
            R.id.menu_item_import -> drag_and_drop_container.import() //Utils(this).longToast("Importing")
            R.id.menu_item_export -> drag_and_drop_container.export() //Utils(this).longToast("Exporting")
        }
        return super.onOptionsItemSelected(item)
    }

    //Starting Timeline Screen
    private fun goToTimelineScreen() {
        val timelineIntent = Intent(this, TimelineActivity::class.java)
        startActivity(timelineIntent)
    }

    //Handling Activity Results for Select Audio Clip --> 'Play Audio File' Tool
    //Why we are handling here? -> Because it requires an Activity to start for result
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        PlayAudioFile(this).onActivityResult(requestCode, resultCode, data)
//    }

//    //Handle results for Selecting Audio Clip
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        //For Selecting Audio From Storage
//        if (requestCode == 123) { //123 or 1 maybe ->> Select Audio Clip REQUEST_SELECT_AUDIO_CLIP
//            if (resultCode == Activity.RESULT_OK) {
//                //Selected Audio Clip
//                val uri = data?.data
//
//                if (uri != null) {
//
//                    Toast.makeText(this, "Uri -> Not Null 2.0", Toast.LENGTH_SHORT).show()
//                    val tv1: TextView = findViewById(R.id.play_audio_file_title_txtview)
//                    tv1.text = "CHANGED FROM ONACTIVITYRESULT: ->"
////                    tv1.visibility = View.GONE
//                }
//            }
//        }
//    }

}
