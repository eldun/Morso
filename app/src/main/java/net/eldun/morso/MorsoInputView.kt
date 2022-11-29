package net.eldun.morso

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.SystemClock
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
    private var centerX = 100.0
    private var centerY = 100.0
    private var downTime: Long = 0
    private var upTime: Long = 0
    private val dotTime: Long = 300
    private val dashTime = 3*dotTime
    private val signalSpaceTimeout = dotTime
    private val letterSpaceTimeout: Long = 3*dotTime
    private val wordSpaceTimeout: Long = 7*dotTime


    private var backgroundText = "Morso"


    fun updateUi(morsoUiState: MorsoUiState) {
        backgroundText = morsoUiState.backgroundText.value.toString()

        invalidate()
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
        centerX = (width / 2.0)
        centerY = (height / 2.0)
        paint.textSize = (min(width, height) / 4.0).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        this.setBackgroundColor(Color.BLACK)
        canvas.drawText(backgroundText, centerX.toFloat(), centerY.toFloat(), paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val onHoldRunnable = Runnable { gestureListener.onHold(event) }
        val shortPauseRunnable = Runnable { gestureListener.onShortPause(event) }
        val longPauseRunnable = Runnable { gestureListener.onLongPause(event) }


        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            downTime = SystemClock.elapsedRealtime()

            // Cancel possible pending runnables
            handler.removeCallbacksAndMessages(null)

            // Call onHold in dashTime ms
            handler.postDelayed(onHoldRunnable, dashTime)
        } else if (event.actionMasked == MotionEvent.ACTION_UP) {
            // Cancel the pending hold runnable and previous pause runnables
            handler.removeCallbacksAndMessages(null)

            upTime = SystemClock.elapsedRealtime()


            // Listen for all taps with no restrictions (slop, triple-taps, etc. - unlike our gesture detector)
            val elapsedTime = upTime - downTime
            if (elapsedTime < dotTime) {
                gestureListener.onSingleTapUp(event)
            }


            // call timeouts if no input has been received
            handler.postDelayed(shortPauseRunnable, letterSpaceTimeout)
            handler.postDelayed(longPauseRunnable, wordSpaceTimeout)

            return true
        }

        // It's up to MorsoGestureListener to decide
        return gestureDetector.onTouchEvent(event)

    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

}
