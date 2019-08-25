package com.app.mmse_draganddrop.command

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.app.mmse_draganddrop.Utils

class PositionDimensionCalculator(private val ctx: Context) {

    /* Returns Position in Dp */
    fun getPosition(myView: View): Pair<Int, Int> {
        val myViewRect = Rect()
        myView.getGlobalVisibleRect(myViewRect)
        val top = Utils(ctx).pxToDp(myViewRect.top)
        val left = Utils(ctx).pxToDp(myViewRect.left)

        return Pair(top, left)
    }

    /* Returns Dimensions in Dp */
    fun getDimensions(view: View): Pair<Int, Int> {
        val width = Utils(ctx).pxToDp(view.width)
        val height = Utils(ctx).pxToDp(view.height)

        return Pair(width, height)
    }

}