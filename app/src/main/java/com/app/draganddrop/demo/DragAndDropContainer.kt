package com.app.draganddrop.demo

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import com.app.draganddrop.R
import com.app.draganddrop.first.OnDragTouchListener
import kotlinx.android.synthetic.main.drag_and_drop_container.view.*
import kotlinx.android.synthetic.main.label_layout.view.*

class DragAndDropContainer: RelativeLayout/*, DraggableClickListener*/ {

//    //Handling Click of Draggable Components
//    override fun customClick(ctx: Context, view: View) { view as TextView
//        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
//        view.text = "CHANGED"
//    }

    constructor(context: Context?) : super(context) { setupContainer() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { setupContainer() }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupContainer() {
        inflate(context, R.layout.drag_and_drop_container, this)
        addLabel() //adding label here
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun addLabel() {
        add_label_btn.setOnClickListener {
            //Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
            val label = Label2(context)
            addView(label)
            //label.label_sample_textview.setOnTouchListener(OnDragTouchListener(this, label))
        }
    }





















    //        add_texter_btn.setOnClickListener {
//            //Create new Text-er, Add view, Set on touch listener to make draggable
//            val a = Texter2(context)
//            addView(a)
//            a.setOnTouchListener(OnDragTouchListener(this, a))
//
//            val b : RelativeLayout = findViewById(R.id.properties_layout_super_main_ll)
//            val c = Properties(context)
//            b.addView(c)  //Adding to properties layout explicitly
//
//            //c.setOnTouchListener(OnDragTouchListener(this, c))
//        }

}