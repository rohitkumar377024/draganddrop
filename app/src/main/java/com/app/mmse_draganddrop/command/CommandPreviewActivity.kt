package com.app.mmse_draganddrop.command

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.draganddrop.R
import kotlinx.android.synthetic.main.activity_command_preview.*

class CommandPreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_preview)

        //Getting Input Command
        val inputCommand = intent.getStringExtra("command")
        //Retrieving Main Blocks of Command using Regex
        val mainBlocksOfCommand = CmdUtils(this).regexMainBlock(inputCommand)
        //Looping through each Main Block
        mainBlocksOfCommand.forEach { mainBlock -> CmdUtils(this).processMainBlock(preview_root_layout, mainBlock) }
    }

}
