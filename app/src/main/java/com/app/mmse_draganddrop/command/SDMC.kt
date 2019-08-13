package com.app.mmse_draganddrop.command

class SDMC {
    //    todo -> use when creating new textview etc

    //        // Some existing RelativeLayout from your layout xml
//        val root: RelativeLayout = findViewById(R.id.preview_root_layout)
//
//        val tv = TextView(this).apply {
//            text = intent.getStringExtra("command") //"CmdUtils Screen"
//            textSize = 42f
//            gravity = Gravity.CENTER
//            typeface = typefaces[Utils.TYPEFACE_LIGHT]
//        }
    //        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
    //todo -> not adding currently to make it invisible
//        root.addView(tv, params)
//todo--------------------------------------used till here------------------------------------------


//    private fun getPosition(parentViewGroup: ViewGroup, childView: View) {
//        val offsetViewBounds = Rect()
//        //returns the visible bounds
//        childView.getDrawingRect(offsetViewBounds)
//        // calculates the relative coordinates to the parent
//        parentViewGroup.offsetDescendantRectToMyCoords(childView, offsetViewBounds)
//
//        val relativeTop = Utils(this).pxToDp(offsetViewBounds.top)
//        val relativeLeft = Utils(this).pxToDp(offsetViewBounds.left)
//
//        val cw = Utils(this).pxToDp(childView.width)
//        val ch = Utils(this).pxToDp(childView.height)
//
//        logger(relativeTop, relativeLeft, cw, ch)
//    }
//
//    private fun logger(relativeTop: Int, relativeLeft: Int, cw: Int, ch: Int) {
//        Log.d("LOG-TIMBER", "TOP -> $relativeTop")
//        Log.d("LOG-TIMBER", "LEFT -> $relativeLeft")
//        Log.d("LOG-TIMBER", "WIDTH -> $cw")
//        Log.d("LOG-TIMBER", "HEIGHT -> $ch")
//    }

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