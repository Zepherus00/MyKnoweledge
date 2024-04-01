package com.example.customviewlesson

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class TestView3(
    context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {
    var listener: Listener? = null
    private val paintCWidth = 100f
    private val paint = Paint()
    private val paintCancelDr = Paint()
    private val paintText = Paint()
    private val paintC = Paint()
    private val startAngle = 0f
    private var mainColor = Color.BLUE
    private var mainColor2 = Color.BLUE
    private var cancelDrawable: Bitmap
    private var images = listOf(
        R.drawable.ic_a,
        R.drawable.ic_b,
        R.drawable.ic_c,
        R.drawable.ic_d,
        R.drawable.ic_e,
        R.drawable.ic_f,
        R.drawable.ic_g,
        R.drawable.ic_h,
    )
    private val sweepAngle = 360f / images.size
    private var buttonClicked = 0

    init {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_cancel)
        cancelDrawable = Bitmap.createBitmap(
            150,
            150,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(cancelDrawable)
        canvas.rotate(-90f, canvas.width / 2f, canvas.height / 2f)
        drawable?.setBounds(0, 0, 150, 150)
        drawable?.draw(canvas)

        mainColor = ContextCompat.getColor(context, R.color.main)
        mainColor2 = ContextCompat.getColor(context, R.color.main2)

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
        for (i in images.indices) {
            paintC.color = if (i == buttonClicked)
                Color.BLACK
            else mainColor
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
        paintC.color = mainColor2
        canvas.drawCircle(
            centerX,
            centerY,
            radius / 1.5f,
            paintC
        )
        canvas.drawBitmap(
            cancelDrawable, centerX - cancelDrawable.width / 2f,
            centerY - cancelDrawable.height / 2f,
            paintCancelDr
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
            val angle = (360f / images.size) * index + ((360f / images.size) / 2f)
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
                buttonClicked = (angle / (360 / images.size)).toInt()
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