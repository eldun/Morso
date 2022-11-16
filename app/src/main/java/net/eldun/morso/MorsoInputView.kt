package net.eldun.morso

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.min


class MorsoInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View (context, attrs, defStyleAttr) {

    private val TAG = "MorsoView"

    val gestureListener =  MorsoGestureListener()
    private val gestureDetector = GestureDetector(context, gestureListener)
    private val longPressTimeout: Long = 1500


    private var backgroundText = "Morso"


    fun updateUi(morsoUiState: MorsoUiState) {
        backgroundText = morsoUiState.backgroundText.value.toString()

        this.invalidate()
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        // Paint styles used for rendering are initialized here. This
        // is a performance optimization, since onDraw() is called
        // for every screen refresh.
        color = Color.GRAY
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
    }

    private var centerX = 100F
    private var centerY = 100F

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = 100
        val desiredHeight = getScreenHeight() / 4

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        //Measure Width
        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> min(desiredWidth, widthSize)
            else -> desiredWidth
        }

        // Measure Height
        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> min(desiredHeight, heightSize)
            else -> desiredHeight
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        Log.i(TAG, "onSizeChanged: $width $height")
        centerX = (width / 2.0).toFloat()
        centerY = (height / 2.0).toFloat()
        paint.textSize = (min(width, height) / 4.0).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        this.setBackgroundColor(Color.BLACK)
        canvas.drawText(backgroundText, centerX, centerY, paint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {

        // call onHold if an ACTION_UP has not been received in longPressTimeout ms
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            handler.postDelayed({ gestureListener.onHold(event) }, longPressTimeout)
        } else if (event.actionMasked == MotionEvent.ACTION_UP) {
            handler.removeCallbacksAndMessages(null)
        }

        if (gestureDetector.onTouchEvent(event)) {
            // The event has been consumed by our simple gesture listener
            return true
        }

        // TODO: determine what to do with ambiguous signals
        // maybe add a progress bar for when the input is held
        return false

    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

}
