package com.app.draganddrop.utils

import android.content.res.Resources

class SizeUtils {
    fun dpToPx(dp: Int): Int { return ((dp * Resources.getSystem().displayMetrics.density).toInt()) }
    fun pxToDp(px: Int): Int { return ((px / Resources.getSystem().displayMetrics.density).toInt()) }
}