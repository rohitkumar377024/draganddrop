package com.app.draganddrop.extras

import android.util.Log
import android.view.MotionEvent
import android.view.View

class NoBoundsSimpleTouchListener/*: View.OnTouchListener*/ {

      //Handling Drag and Drop Operation --> Smooth and Precise
//    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        when (event?.action) {
//            MotionEvent.ACTION_DOWN -> {
//                lastAction = MotionEvent.ACTION_DOWN
//                dX = v?.x?.minus(event.rawX) //actual x - raw x [difference between cursor and actual drag]
//                dY = v?.y?.minus(event.rawY) //actual x - raw x [difference between cursor and actual drag]
//            }
//            MotionEvent.ACTION_MOVE -> {
//                lastAction = MotionEvent.ACTION_MOVE  //v?.x = event.rawX + dX!! //v?.y = event.rawY + dY!!
//
//
//            }
//            MotionEvent.ACTION_UP ->
//                if (lastAction == MotionEvent.ACTION_DOWN) { //Which means it is --> Clicked
//                    showPropertiesPane() //Show Label2 Pane When Clicked
//                }
//        }
//        return true
//    }

}