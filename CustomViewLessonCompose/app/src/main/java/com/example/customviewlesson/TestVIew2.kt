package com.example.customviewlesson

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class TestView2(
    context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {
    var listener: Listener? = null
    private val paintCWidth = 100f
    private val paint = Paint()
    private val paintText = Paint()
    private val paintC = Paint()
    private val startAngle = 0f
    private val colors = listOf(
        Color.RED,
        Color.BLUE,
        Color.GREEN,
        Color.CYAN,
        Color.BLACK,
        Color.GREEN,
        Color.DKGRAY,
        Color.YELLOW
    )
    private val sweepAngle = 360f / colors.size
    private var buttonClicked = 0

    init {
        paintText.style = Paint.Style.FILL
        paintText.color = Color.WHITE
        paintText.textSize = 30f

        paint.style = Paint.Style.STROKE
        paint.color = Color.GRAY
        paint.strokeWidth = 5f

        paintC.color = Color.RED
        paintC.strokeWidth = paintCWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircleButton(canvas)
    }

    private fun drawCircleButton(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = width / 2f
        val radius = (width.coerceAtMost(height) / 2f) - paintCWidth / 2f
        paintC.style = Paint.Style.STROKE
        for (i in colors.indices) {
            paintC.color = if (i == buttonClicked)
                Color.GRAY
            else colors[i]
            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                startAngle + i * sweepAngle,
                sweepAngle,
                false,
                paintC
            )
        }
        paintC.style = Paint.Style.FILL
        paintC.color = Color.GRAY
        canvas.drawCircle(
            centerX,
            centerY,
            radius / 1.7f,
            paintC
        )
        drawMenuText(canvas)
    }

    private fun drawCenterText(text: String, canvas: Canvas) {
        val rect = Rect()
        paintText.getTextBounds(text, 0, text.length, rect)
        canvas.drawText(
            text,
            (width / 2) - rect.exactCenterX(),
            (height / 2) - rect.exactCenterY(),
            paintText
        )
    }

    private fun drawMenuText(canvas: Canvas) {
        TextUtils.menuList.forEachIndexed { index, text ->
            val rect = Rect()
            paintText.getTextBounds(text, 0, text.length, rect)
            val angle = (360f / colors.size) * index + ((360f / colors.size) / 2f)
            val coordinate = getXY(angle)
            canvas.rotate(-90f, coordinate.first, coordinate.second)
            canvas.drawText(
                text,
                coordinate.first - rect.exactCenterX(),
                coordinate.second - rect.exactCenterY(),
                paintText
            )
            canvas.rotate(90f, coordinate.first, coordinate.second)
        }
    }

    private fun getXY(
        angle: Float
    ): Pair<Float, Float> {
        val centerX = (width / 2f)
        val centerY = (height / 2f)
        val radius = (width / 2f) - (paintCWidth / 2f)

        val x = centerX + radius * cos(Math.toRadians(angle.toDouble())).toFloat()
        val y = centerY + radius * sin(Math.toRadians(angle.toDouble())).toFloat()
        return Pair(x, y)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val centerX = width / 2f
        val centerY = width / 2f
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val angle = (Math.toDegrees(
                    atan2(
                        y - centerY,
                        x - centerX
                    ).toDouble()
                ) + 360) % 360
                buttonClicked = (angle / (360 / colors.size)).toInt()
                listener?.onClick(buttonClicked)
                Log.d("MyLog", "Angle: $angle")
                invalidate()
            }
        }
        return true
    }

    interface Listener {
        fun onClick(index: Int)
    }
}