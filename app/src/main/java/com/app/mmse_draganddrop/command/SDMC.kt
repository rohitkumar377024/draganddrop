package com.app.mmse_draganddrop.command

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.app.mmse_draganddrop.Utils

class SDMC(private val ctx: Context) {

    fun getPosition(parentViewGroup: ViewGroup, childView: View) {

        //todo tomorrow ->
        //cw and ch works below -> hence childview.width and height works perfectly
        //however, relativeTop and left not working -> hence top and left 0 both
        //fix it

        val offsetViewBounds = Rect()
        //returns the visible bounds
        childView.getDrawingRect(offsetViewBounds)
        // calculates the relative coordinates to the parent
        parentViewGroup.offsetDescendantRectToMyCoords(childView, offsetViewBounds)

        val relativeTop = Utils(ctx).pxToDp(offsetViewBounds.top)
        val relativeLeft = Utils(ctx).pxToDp(offsetViewBounds.left)

        val cw = Utils(ctx).pxToDp(childView.width)
        val ch = Utils(ctx).pxToDp(childView.height)

        logger(relativeTop, relativeLeft, cw, ch)
    }

    private fun logger(relativeTop: Int, relativeLeft: Int, cw: Int, ch: Int) {
        Log.d("LOG-TIMBER", "TOP -> $relativeTop")
        Log.d("LOG-TIMBER", "LEFT -> $relativeLeft")
        Log.d("LOG-TIMBER", "WIDTH -> $cw")
        Log.d("LOG-TIMBER", "HEIGHT -> $ch")
    }

    //todo - pasted from commandactivity directly

    //        /* Waiting for layout to be rendered, first */
//        val vto = command_root_relative_layout.viewTreeObserver
//        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                command_root_relative_layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
//                /* Calling getPosition() here */
////                getPosition(command_root_relative_layout, command_title_txtview)
//            }
//        })

//        setWidthHeight()
}