package com.app.draganddrop.first

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.app.draganddrop.demo.label.Label2
import kotlin.math.abs

class LabelTouchListener @JvmOverloads constructor(
    label: Label2, /*cb: DraggableClickListener, */view: View, parent: View = view.parent as View,
    onDragActionListener: OnDragActionListener? = null) : View.OnTouchListener {

    val label2: Label2 = label
    private var mView: View? = null
    private var mParent: View? = null
    private var isDragging: Boolean = false
    private var isInitialized = false

    private var width: Int = 0
    private var xWhenAttached: Float = 0f
    private var maxLeft: Float = 0f
    private var maxRight: Float = 0f
    private var dX: Float = 0f

    private var height: Int = 0
    private var yWhenAttached: Float = 0f
    private var maxTop: Float = 0f
    private var maxBottom: Float = 0f
    private var dY: Float = 0f

    private var mOnDragActionListener: OnDragActionListener? = null

    private var mDownX: Float = 0f
    private var mDownY: Float = 0f
    private var SCROLL_THRESHOLD = 10f
    private var isOnClick: Boolean = false

//    private var callback: DraggableClickListener

    //Callback used to indicate when the drag is finished
    interface OnDragActionListener {
         //The View Dragged
        fun onDragStart(view: View?)
        //Called when drag event is completed
        fun onDragEnd(view: View?)
    }

    init {
        initListener(view, parent)
        setOnDragActionListener(onDragActionListener)

//        this.callback = cb
    }

    //todo -> handling click here in a separate function for clarity and separation of code
    private fun click() {
        //Showing the Properties Pane
        label2.showPropertiesPane()
    }

    private fun setOnDragActionListener(onDragActionListener: OnDragActionListener?) {
        mOnDragActionListener = onDragActionListener
    }

    private fun initListener(view: View, parent: View) {
        mView = view
        mParent = parent
        isDragging = false
        isInitialized = false
    }

    //todo -> was private
    private fun updateBounds() {
        updateViewBounds()
        updateParentBounds()
        isInitialized = true
    }

    private fun updateViewBounds() {
        width = mView!!.width
        xWhenAttached = mView!!.x
        dX = 0f

        height = mView!!.height
        yWhenAttached = mView!!.y
        dY = 0f
    }

    private fun updateParentBounds() {
        maxLeft = 0f
        maxRight = maxLeft + mParent!!.width

        maxTop = 0f
        maxBottom = maxTop + mParent!!.height
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (isDragging) {
            val bounds = FloatArray(4)
            // LEFT
            bounds[0] = event.rawX + dX
            if (bounds[0] < maxLeft) {
                bounds[0] = maxLeft
            }
            // RIGHT
            bounds[2] = bounds[0] + width
            if (bounds[2] > maxRight) {
                bounds[2] = maxRight
                bounds[0] = bounds[2] - width
            }
            // TOP
            bounds[1] = event.rawY + dY
            if (bounds[1] < maxTop) {
                bounds[1] = maxTop
            }
            // BOTTOM
            bounds[3] = bounds[1] + height
            if (bounds[3] > maxBottom) {
                bounds[3] = maxBottom
                bounds[1] = bounds[3] - height
            }

            when (event.action) {
                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                    onDragFinish()
                    if (isOnClick) {
                        //TODO onClick code
                        Log.d("workx", "clicked")

                        click()

//                        callback.customClick(v.context, v) //todo -> CALLBACK HERE
                    }
                }

                MotionEvent.ACTION_MOVE -> {
                    if (isOnClick && (abs(mDownX - event.x) > SCROLL_THRESHOLD
                                    || abs(mDownY - event.y) > SCROLL_THRESHOLD)) {
                        Log.i("workx", "movement detected")
                        isOnClick = false
                    }
                    mView!!.animate().x(bounds[0]).y( bounds[1]).setDuration(0).start()
                }
            }
            return true
        } else {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mDownX = event.x
                    mDownY = event.y
                    isOnClick = true

                    isDragging = true
                    if (!isInitialized) {
                        updateBounds()
                    }
                    dX = v.x - event.rawX
                    dY = v.y - event.rawY
                    if (mOnDragActionListener != null) {
                        mOnDragActionListener!!.onDragStart(mView)
                    }
                    return true
                }
            }
        }
        return false
    }

    private fun onDragFinish() {
        if (mOnDragActionListener != null) {
            mOnDragActionListener!!.onDragEnd(mView)
        }

        dX = 0f
        dY = 0f
        isDragging = false
    }

}