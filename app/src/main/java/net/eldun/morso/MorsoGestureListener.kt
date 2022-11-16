package net.eldun.morso

import android.inputmethodservice.InputMethodService
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.inputmethod.InputConnection

class MorsoGestureListener : GestureDetector.SimpleOnGestureListener() {

    val TAG = "MorsoGestureListener"

    private var morsoUiState = MorsoUiState
    lateinit var inputConnection: InputConnection

    /**
     * Notified when a tap occurs with the down [MotionEvent]
     * that triggered it. This will be triggered immediately for
     * every down event. All other events should be preceded by this.
     *
     * @param e The down motion event.
     */
    override fun onDown(e: MotionEvent): Boolean {
        return true
    }

    /**
     * Notified when a tap occurs with the up [MotionEvent]
     * that triggered it.
     *
     * @param e The up motion event that completed the first tap
     * @return true if the event is consumed, else false
     */
    override fun onSingleTapUp(e: MotionEvent): Boolean {
        Log.d(TAG, "onSingleTapUp")
        morsoUiState.backgroundText.value = "tapped"
        inputConnection.commitText("!", 1)
        return true
    }

    fun onHold(e: MotionEvent): Boolean {

        Log.d(TAG, "onHold")
        return true

    }
}
