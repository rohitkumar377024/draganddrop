package com.app.draganddrop.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

class Utils(private val ctx: Context) {

    /* Hide/Show */
    fun hide(view: View) { view.visibility = View.GONE }
    fun show(view: View) { view.visibility = View.VISIBLE }

    /* Enable/Disable */
    fun enable(view: View) { view.alpha = 1f; view.isClickable = true }
    fun disable(view: View) { view.alpha = 0.4f; view.isClickable = false }

    /* Toasts */
    fun toast(message: String) = Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
    fun longToast(message: String) = Toast.makeText(ctx, message, Toast.LENGTH_LONG).show()

    /* Soft Keyboard */
    fun hideSoftKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    fun showSoftKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

}