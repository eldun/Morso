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

        if (updateCandidates(MorseSignal.DOT))
            showUserInput(".")

        return true
    }


    fun onHold(e: MotionEvent): Boolean {
        Log.d(TAG, "onHold")


        if (updateCandidates(MorseSignal.DASH))
            showUserInput("-")

        return true

    }

    fun onShortPause(e: MotionEvent): Boolean {
        Log.d(TAG, "onShortPause")
        inputConnection.commitText(morsoUiState.currentCandidateText.value, 1)

        morsoUiState.reset()
        return true
    }

    fun onLongPause(e: MotionEvent): Boolean {
        Log.d(TAG, "onLongPause")
        inputConnection.commitText(" ", 1)

        return true
    }

    private fun showUserInput(input: String) {

        if (morsoUiState.backgroundText.value.equals(morsoUiState.DEFAULT_BACKGROUND_TEXT))
            morsoUiState.backgroundText.value = input
        else
            morsoUiState.backgroundText.value += input

    }

    /**
     * Update current candidate, dot candidate, and dash candidate IF the character at @param signal
     * from the current sequence is not null.
     *
     * @param signal the newest signal added to the sequence
     *
     * @return true if the candidates were updated, otherwise false
     */
    private fun updateCandidates(signal: MorseSignal): Boolean {

        if (signal == MorseSignal.DOT) {
            val dotChild = Character.getDotChild(morsoUiState.currentCandidateText.value)

            if (dotChild == Character.NULL) {
                return false
            }

            else {
                val newCurrent = dotChild.toString()

                morsoUiState.currentCandidateText.value = newCurrent

                morsoUiState.dotCandidateText.value = Character.getDotChild(newCurrent).toString()
                morsoUiState.dashCandidateText.value = Character.getDashChild(newCurrent).toString()

                return true
            }
        }

        else if (signal == MorseSignal.DASH) {
            val dashChild = Character.getDashChild(morsoUiState.currentCandidateText.value)

            if (dashChild == Character.NULL) {
                return false
            }

            else {
                val newCurrent = dashChild.toString()

                morsoUiState.currentCandidateText.value = newCurrent

                morsoUiState.dotCandidateText.value = Character.getDotChild(newCurrent).toString()
                morsoUiState.dashCandidateText.value = Character.getDashChild(newCurrent).toString()

                return true
            }
        }


        return false
    }
}
