package com.app.draganddrop.utils

import android.graphics.Rect
import android.util.Log
import android.view.View

class CoordinateUtils {

    fun getCoordinatesInDp(v: View): Rect {
        //Rect is perfect for storing 4 integer values -> like 'top, bottom, left, right' here
        val coordinatesRect = Rect()

        //v.getLocalVisibleRect(coordinatesRect) //For coordinates location relative to the parent
        v.getGlobalVisibleRect(coordinatesRect) //For coordinates location relative to the screen/display

        val widthInDp = SizeUtils().pxToDp(coordinatesRect.width())
        val heightInDp = SizeUtils().pxToDp(coordinatesRect.height())
        val topInDp = SizeUtils().pxToDp(coordinatesRect.top)
        val bottomInDp = SizeUtils().pxToDp(coordinatesRect.bottom)
        val leftInDp = SizeUtils().pxToDp(coordinatesRect.left)
        val rightInDp = SizeUtils().pxToDp(coordinatesRect.right)

        //todo -> add these in a new log() function

        Log.d("coordinate-dp -> width", "$widthInDp")
        Log.d("coordinate-dp -> height", "$heightInDp")
        Log.d("coordinate-dp -> top", "$topInDp")
        Log.d("coordinate-dp -> bottom", "$bottomInDp")
        Log.d("coordinate-dp -> left", "$leftInDp")
        Log.d("coordinate-dp -> right", "$rightInDp")

        return coordinatesRect
    }

    fun getCoordinatesInPx(v: View): Rect {
        //Rect is perfect for storing 4 integer values -> like 'top, bottom, left, right' here
        val coordinatesRect = Rect()

        //v.getLocalVisibleRect(coordinatesRect) //For coordinates location relative to the parent
        v.getGlobalVisibleRect(coordinatesRect) //For coordinates location relative to the screen/display

        val widthInPx = coordinatesRect.width()
        val heightInPx = coordinatesRect.height()
        val topInPx = coordinatesRect.top
        val bottomInPx = coordinatesRect.bottom
        val leftInPx = coordinatesRect.left
        val rightInPx = coordinatesRect.right

        Log.d("coordinate-px -> width", "$widthInPx")
        Log.d("coordinate-px -> height", "$heightInPx")
        Log.d("coordinate-px -> top", "$topInPx")
        Log.d("coordinate-px -> bottom", "$bottomInPx")
        Log.d("coordinate-px -> left", "$leftInPx")
        Log.d("coordinate-px -> right", "$rightInPx")

        return coordinatesRect
    }

}