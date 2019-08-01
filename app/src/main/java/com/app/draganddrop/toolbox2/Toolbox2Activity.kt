package com.app.draganddrop.toolbox2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.app.draganddrop.demo.play_audio_file.PlayAudioFile
import kotlinx.android.synthetic.main.activity_toolbox2.*
import android.widget.Toast
import com.app.draganddrop.R
import com.app.draganddrop.demo.label.LabelListItem
import com.app.draganddrop.utils.CoordinateUtils
import kotlinx.android.synthetic.main.play_audio_file_layout.*

class Toolbox2Activity : AppCompatActivity() {

    //todo -> tracks the CustomViews being added to the DragAndDropContainer
    private val itemsList = arrayListOf<String>()

    private val customViewList = arrayListOf<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbox2)

        tool_box_label.setOnClickListener { //Label
            drag_and_drop_container.addLabelOriginal() 

            addItemToList(LabelListItem().text)
        }

        tool_box_play_audio_file.setOnClickListener { //Play Audio
            drag_and_drop_container.addPlayAudioFileOriginal()

            addItemToList("Play Audio File added!")
        }

        toolbox_done_dummy_btn.setOnClickListener { //saveLayout() todo -> commented
            showItemsList()
        }
    }

    private fun addItemToList(item: String) = itemsList.add(item)
    private fun showItemsList() {
//        for (x in itemsList) {
//            Log.d("DDC-13", "Item: $x")
//        }

        itemsList.forEach { Log.d("DDC-13", "Item: $it") }
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

//    private fun saveLayout() {
//        Toast.makeText(applicationContext, "Saving Layout, just a minute", Toast.LENGTH_SHORT).show()
//
//        //Getting top-bottom-right-left coordinates of the view
//        val px = CoordinateUtils().getCoordinatesInPx(textview_save_test_1)
//        val dp = CoordinateUtils().getCoordinatesInDp(textview_save_test_1)
//
//        val intent = Intent(this, ToolboxSavedResultActivity::class.java)
//        intent.putExtra("px_width", px.width())
//        intent.putExtra("px_height", px.height())
//        intent.putExtra("px_top", px.top)
//        intent.putExtra("px_bottom", px.bottom)
//        intent.putExtra("px_left", px.left)
//        intent.putExtra("px_right", px.right)
//        startActivity(intent)
//    }

}
