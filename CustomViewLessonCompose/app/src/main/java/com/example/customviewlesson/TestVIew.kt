package com.example.customviewlesson

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class TestView(
    context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {
    private val paintR = Paint()
    private val paintC = Paint()

    init {
        paintR.style = Paint.Style.STROKE
        paintR.strokeWidth = 5f
        paintR.color = Color.GRAY

        paintC.style = Paint.Style.FILL
        paintC.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintR)
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), 100f, paintC)
    }
}