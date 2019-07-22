package com.app.draganddrop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
























    //todo --> below is a very good code snippet written for real-time smooth drag and drop
    //remember - let the class implement OnTouchListener and setOnTouchListener(this) on draggable stuff

//    private var dX: Float? = null
//    private var dY: Float? = null
//
//    private var lastAction: Int? = null

//    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        when (event?.action) {
//            MotionEvent.ACTION_DOWN -> {
//                lastAction = MotionEvent.ACTION_DOWN
//                dX = v?.x?.minus(event.rawX) //actual x - raw x [difference between cursor and actual drag]
//                dY = v?.y?.minus(event.rawY) //actual x - raw x [difference between cursor and actual drag]
//            }
//            MotionEvent.ACTION_MOVE -> {
//                lastAction = MotionEvent.ACTION_MOVE
//                v?.x = event.rawX + dX!!
//                v?.y = event.rawY + dY!!
//            }
//            MotionEvent.ACTION_UP -> {
//                if (lastAction == MotionEvent.ACTION_DOWN) { //Which means it is --> Clicked
//                    Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//        return true
//    }


}