package com.bangkit.aktivio.core.utils

import android.app.Activity
import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bangkit.aktivio.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

object Extensions {

    fun Activity.toast(title: String, message: String, type: MotionToastStyle) {
        MotionToast.createColorToast(this,
            title,
            message,
            type,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(this, R.font.poppins))
    }
    fun String.applyRedColorToText(context: Context): SpannableString {
        val cleanText = this.replace("<red>", "")
        val spannableString = SpannableString(cleanText)
        var start = this.indexOf("<red>")
        val tagLength = "<red>".length

        var currentIndex = 0
        while (start != -1) {
            val end = this.indexOf("<red>", start + tagLength)
            if (end != -1) {
                val adjustedStart = start - (tagLength * currentIndex)
                val adjustedEnd = end - (tagLength * (currentIndex + 1))

                spannableString.setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(context, R.color.red500)),
                    adjustedStart,
                    adjustedEnd,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                currentIndex++
                start = this.indexOf("<red>", end + tagLength)
            } else {
                start = -1
            }
        }

        return spannableString
    }
}

