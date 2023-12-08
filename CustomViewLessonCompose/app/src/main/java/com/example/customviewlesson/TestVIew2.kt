package com.example.customviewlesson

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TestVIew2(
    context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {
    private val paintR = Paint()
    private val paintC = Paint()
    private var startAngle = 0f
    private val colors = listOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW)

    init {
        paintR.style = Paint.Style.STROKE
        paintR.strokeWidth = 5f
        paintR.color = Color.GRAY

        paintC.style = Paint.Style.FILL
        paintC.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircleButton(canvas)
    }

    private fun drawCircleButton(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = width / 2f
        val radius = width.coerceAtMost(height) / 2f

        for (i in colors.indices) {
            paintC.color = colors[i]

            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                startAngle + i * 90,
                90f,
                true,
                paintC
            )
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val centerX = width / 2f
        val centerY = width / 2f

        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {

            }
            MotionEvent.ACTION_UP -> {
                
            }
        }
        return true
    }
}