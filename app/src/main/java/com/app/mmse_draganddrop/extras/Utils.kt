package com.app.mmse_draganddrop.extras

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.app.draganddrop.R

class Utils(private val ctx: Context) {

    companion object {
        /* Typeface Constants */
        const val TYPEFACE_THIN = 0
        const val TYPEFACE_LIGHT = 1
        const val TYPEFACE_MEDIUM = 2
        const val TYPEFACE_BOLD = 3
    }

    /* Typefaces */
    val typefaces = arrayListOf(
        ResourcesCompat.getFont(ctx, R.font.helvetica_neue_thin),
        ResourcesCompat.getFont(ctx, R.font.helvetica_neue_light),
        ResourcesCompat.getFont(ctx, R.font.helvetica_neue_medium),
        ResourcesCompat.getFont(ctx, R.font.helvetica_neue_bold))

    /* Conversions */
    fun dpToPx(dp: Int) = (dp * ctx.resources.displayMetrics.density).toInt()
    fun pxToDp(px: Int) = (px / ctx.resources.displayMetrics.density).toInt()

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