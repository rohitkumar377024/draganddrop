package com.app.draganddrop.demo

import android.view.View

class VisibilityUtils {
    fun hide(view: View) { view.visibility = View.GONE } // --> Hides a view
    fun show(view: View) { view.visibility = View.VISIBLE } // --> Shows a view
}