package net.eldun.morso

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import kotlin.math.min


class MorsoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View (context, attrs, defStyleAttr) {

    val TAG = "MorsoView"

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
        val desiredWidth = 100;
        val desiredHeight = getScreenHeight() / 4;

        val widthMode = MeasureSpec.getMode(widthMeasureSpec);
        val widthSize = MeasureSpec.getSize(widthMeasureSpec);
        val heightMode = MeasureSpec.getMode(heightMeasureSpec);
        val heightSize = MeasureSpec.getSize(heightMeasureSpec);

        var width : Int;
        var height : Int;

        //Measure Width
        when (widthMode) {
            MeasureSpec.EXACTLY -> width = widthSize;
            MeasureSpec.AT_MOST -> width = Math.min(desiredWidth, widthSize);
            else -> width = desiredWidth;
        }

        // Measure Height
        when (heightMode) {
            MeasureSpec.EXACTLY -> height = heightSize;
            MeasureSpec.AT_MOST -> height = Math.min(desiredHeight, heightSize);
            else -> height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        Log.i(TAG, "onSizeChanged: "+width+" "+height)
        centerX = (width / 2.0).toFloat()
        centerY = (height / 2.0).toFloat()
        paint.textSize = (min(width, height) / 4.0).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        this.setBackgroundColor(Color.BLACK)
        canvas.drawText("Morso", centerX, centerY, paint)
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().getDisplayMetrics().heightPixels
    }

}
