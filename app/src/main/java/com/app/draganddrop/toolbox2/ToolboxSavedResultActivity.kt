package com.app.draganddrop.toolbox2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.app.draganddrop.R
import kotlinx.android.synthetic.main.activity_toolbox_saved_result.*

class ToolboxSavedResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbox_saved_result)

        //val a = TextView(this)

        val width = intent.getIntExtra("px_width", 0)
        val height = intent.getIntExtra("px_height", 0)
        val top = intent.getIntExtra("px_top", 0)
        val bottom = intent.getIntExtra("px_bottom", 0)
        val left = intent.getIntExtra("px_left", 0)
        val right =intent.getIntExtra("px_right", 0)

        val layoutParams = RelativeLayout.LayoutParams(width, height)
        layoutParams.setMargins(left, top, right, bottom)
        restoring_textview.layoutParams = layoutParams

        //saved_result_master2_constraintlayout.addView(a)
    }
}
