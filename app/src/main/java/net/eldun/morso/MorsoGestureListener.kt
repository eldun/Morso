package net.eldun.morso

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.inputmethod.InputConnection
import net.eldun.morso.enums.Character
import net.eldun.morso.enums.MorseSignal

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
        morsoUiState.backgroundText.value.apply { "." }

        updateCandidates(MorseSignal.DOT)

        inputConnection.commitText("!", 1)

        return true
    }



    fun onHold(e: MotionEvent): Boolean {

        updateCandidates(MorseSignal.DASH)
        Log.d(TAG, "onHold")
        return true

    }

    fun onShortPause(e: MotionEvent): Boolean {
        Log.d(TAG, "onShortPause")
        morsoUiState.reset()
        return true
    }

    fun onLongPause(e: MotionEvent): Boolean {
        Log.d(TAG, "onLongPause")
        inputConnection.commitText(" ", 1)

        return true
    }

    private fun updateCandidates(signal: MorseSignal) {

        if (signal == MorseSignal.DOT){
            morsoUiState.currentCandidateText.value = morsoUiState.dotCandidateText.value

            val newCurrent = Character.fromString(morsoUiState.currentCandidateText.value.toString())

            morsoUiState.dotCandidateText.value = Character.getDotChild(newCurrent!!).toString()
            morsoUiState.dashCandidateText.value = Character.getDashChild(newCurrent!!).toString()
        }

        else if (signal == MorseSignal.DASH){
            morsoUiState.currentCandidateText.value = morsoUiState.dashCandidateText.value

            val newCurrent = Character.fromString(morsoUiState.currentCandidateText.value.toString())

            morsoUiState.dotCandidateText.value = Character.getDotChild(newCurrent!!).toString()
            morsoUiState.dashCandidateText.value = Character.getDashChild(newCurrent!!).toString()
        }
    }
}
