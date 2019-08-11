package com.app.mmse_draganddrop.command

import android.util.Log

class Command {

    fun checkIfNewCommand(trimmedCommand: String) {
        if (trimmedCommand.first() == '#') {
            Log.d("Hashtag", "New")
        }
    }

    fun findInBetween(start: String, end: String) {

    }

}