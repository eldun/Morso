package net.eldun.morso

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.min

class MorsoCandidateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatButton (context, attrs, defStyleAttr) {



    private val TAG = "MorsoCandidateView"

    init {
        setBackgroundColor(Color.DKGRAY)
        setTextColor(Color.WHITE)
        gravity = Gravity.CENTER
    }

}