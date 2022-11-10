package net.eldun.morso

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class MorsoGestureListener : GestureDetector.SimpleOnGestureListener() {

    val TAG = "MorsoGestureListener"

    var morsoUiState = MorsoUiState()

    override fun onDown(e: MotionEvent): Boolean {
        Log.d(TAG, "downMotion detected!")

        return true
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        Log.d(TAG, "tap detected!")
        morsoUiState.backgroundText.value = "tapped"
        return true
    }
}