package com.bangkit.aktivio.core.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast

class RsToast (private val context: Context) {
    fun show(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}