package com.app.draganddrop.demo

import android.content.Context
import android.view.View

interface DraggableClickListener {
    fun customClick(ctx: Context, view: View)
}