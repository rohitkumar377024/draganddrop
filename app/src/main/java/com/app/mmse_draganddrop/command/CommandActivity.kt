package com.app.mmse_draganddrop.command

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.draganddrop.R
import kotlinx.android.synthetic.main.activity_command.*
import android.view.*

class CommandActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command)
    }

    //Creating Menu to Go To Preview Activity
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_command, menu)
        return true
    }

    //Handling Menu Item Clicks
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_command_go_to_preview -> { startCommandPreviewScreen() }
        }
        return super.onOptionsItemSelected(item)
    }

    //Starting Preview Screen [Also passing command to it]
    private fun startCommandPreviewScreen() {
        val previewIntent = Intent(this, CommandPreviewActivity::class.java)
        previewIntent.putExtra("command", getInputCommandAtOnce())
        startActivity(previewIntent)
    }

    /*Getting Whole Command Input At Once */
    private fun getInputCommandAtOnce() = command_activity_input_edittext.text.toString()

}
