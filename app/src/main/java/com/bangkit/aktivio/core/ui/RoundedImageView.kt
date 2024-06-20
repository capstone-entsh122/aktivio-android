package com.bangkit.aktivio.core.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class RoundedImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
    private val paint = Paint().apply {
        isAntiAlias = true
        xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    }
    private val roundRect = RectF()
    private val path = Path()
    private val radius = 10f

    override fun onDraw(canvas: Canvas) {
        val saveCount = canvas.saveLayer(null, null)
        super.onDraw(canvas)
        roundRect.set(0f, 0f, width.toFloat(), height.toFloat())
        path.addRoundRect(roundRect, radius, radius, Path.Direction.CW)
        canvas.drawPath(path, paint)
        canvas.restoreToCount(saveCount)
    }
}
