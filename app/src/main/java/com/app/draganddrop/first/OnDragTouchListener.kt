package com.app.draganddrop.first

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import kotlin.math.abs

class OnDragTouchListener @JvmOverloads constructor(
    label: Label, view: View, parent: View = view.parent as View,
    onDragActionListener: OnDragActionListener? = null) : View.OnTouchListener {

    val label2: Label = label
    //pushing

//    private var lastAction: Int? = null //Tracks last action for differentiating b/w Touch and Click

    private var mView: View? = null
    private var mParent: View? = null
    private var isDragging: Boolean = false
    private var isInitialized = false

    private var width: Int = 0
    private var xWhenAttached: Float = 0.toFloat()
    private var maxLeft: Float = 0.toFloat()
    private var maxRight: Float = 0.toFloat()
    private var dX: Float = 0.toFloat()

    private var height: Int = 0
    private var yWhenAttached: Float = 0.toFloat()
    private var maxTop: Float = 0.toFloat()
    private var maxBottom: Float = 0.toFloat()
    private var dY: Float = 0.toFloat()

    private var mOnDragActionListener: OnDragActionListener? = null

    private lateinit var mCtx: Context

    private var mDownX: Float = 0.toFloat()
    private var mDownY: Float = 0.toFloat()
    private var SCROLL_THRESHOLD = 10f
    private var isOnClick: Boolean = false

//    private var lastTouchDown: Long = 0
//    private val CLICK_ACTION_THRESHHOLD = 200

//    val LONG_PRESS_DELAY = 500
//
//    val handler = Handler()
//    var boundaries: Rect? = null
//
//    var onTap = Runnable {
//        handler.postDelayed(onLongPress, LONG_PRESS_DELAY - ViewConfiguration.getTapTimeout().toLong())
//    }
//
//    var onLongPress = Runnable {
//        // Long Press
//        Log.d("workx", "you clicked!")
//        label2.showPropertiesPane() //todo --> weird why not working there
//    }

    /**
     * Callback used to indicate when the drag is finished
     */
    interface OnDragActionListener {
        /**
         * Called when drag event is started
         *
         * @param view The view dragged
         */
        fun onDragStart(view: View?)

        /**
         * Called when drag event is completed
         *
         * @param view The view dragged
         */
        fun onDragEnd(view: View?)
    }

//    constructor(view: View, onDragActionListener: OnDragActionListener)
//            : this(view, view.parent as View, onDragActionListener)

    init {
        initListener(view, parent)
        setOnDragActionListener(onDragActionListener)
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
                    onDragFinish() //existed below together earlier

                    if (isOnClick) {
                        //TODO onClick code
                        Log.d("workx", "clicked")
                        label2.showPropertiesPane() //fix tomorrow
                    }

//                    handler.removeCallbacks(onLongPress)
//                    handler.removeCallbacks(onTap)
                }
//                MotionEvent.ACTION_UP -> {
//                    onDragFinish()

//                    handler.removeCallbacks(onLongPress)
//                    handler.removeCallbacks(onTap)

//                    Log.d("workx", "you clicked!")
//                    label2.showPropertiesPane() //todo --> weird why not working there

//                    if (System.currentTimeMillis() - lastTouchDown < CLICK_ACTION_THRESHHOLD) {
//                        Log.d("workx", "you clicked!")
//                        label2.showPropertiesPane() //todo --> weird why not working there
//                    }

//                    if (lastAction == MotionEvent.ACTION_DOWN && !isDragging) { //Which means it is --> Clicked
                        //showPropertiesPane() //Show Properties Pane When Clicked
                        //Toast.makeText(mCtx, "Lets see", Toast.LENGTH_SHORT).show()
//                        Log.d("workx", "workx")
//                        label2.showPropertiesPane() //fix tomorrow
//                    }
//                }
                MotionEvent.ACTION_MOVE -> {

                    if (isOnClick && (abs(mDownX - event.x) > SCROLL_THRESHOLD || Math.abs(mDownY - event.y) > SCROLL_THRESHOLD)) {
                        Log.i("workx", "movement detected")
                        isOnClick = false
                    }

//                    lastAction = MotionEvent.ACTION_MOVE

//                    if (!boundaries!!.contains(v.left + event.x.toInt(), v.top + event.y.toInt())) {
//                        handler.removeCallbacks(onLongPress)
//                        handler.removeCallbacks(onTap)
//                    }

                    mView!!.animate().x(bounds[0]).y(bounds[1]).setDuration(0).start()
                }
            }
            return true
        } else {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
//                    lastTouchDown = System.currentTimeMillis();
//                    lastAction = MotionEvent.ACTION_DOWN

//                    boundaries = Rect(v.left, v.top, v.right, v.bottom)
//                    handler.postDelayed(onTap, ViewConfiguration.getTapTimeout().toLong())

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