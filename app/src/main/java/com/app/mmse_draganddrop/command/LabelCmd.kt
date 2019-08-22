package com.app.mmse_draganddrop.command

data class LabelCmd(val text: String, val textSize: Float,
                    val fontWeight: String, val width: Int,
                    val height: Int, val top: Int, val left: Int)