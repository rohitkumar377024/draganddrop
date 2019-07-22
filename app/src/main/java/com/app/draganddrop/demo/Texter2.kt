package com.app.draganddrop.demo

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.app.draganddrop.R
import kotlinx.android.synthetic.main.texter2_layout.view.*

class Texter2 : RelativeLayout {

    constructor(context: Context?) : super(context) { setupTexter2() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { setupTexter2() }

    private fun setupTexter2() {
        inflate(context, R.layout.texter2_layout, this) //Root was null, hence it was not showing - smh

        var x = 1

        texter2_btn.setOnClickListener {
            texter_2_test_txtview.text = "Clicked: $x"
            x++
        }
    }

}