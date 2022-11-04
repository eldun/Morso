package net.eldun.morso

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class MorsoGestureListener : GestureDetector.SimpleOnGestureListener() {

    val TAG = "MorsoGestureListener"

    override fun onDown(e: MotionEvent): Boolean {
        Log.i(TAG, "downMotion detected!")

        return true
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        Log.i(TAG, "tap detected!")
        return true
    }
}