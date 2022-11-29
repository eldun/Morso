package net.eldun.morso

import android.inputmethodservice.InputMethodService
import android.util.Log
import android.view.View

class MorsoIME : InputMethodService() {
    private val TAG = "MorsoIME"
    lateinit var morsoInputView: MorsoInputView

    lateinit var candidatesLayout: View
    private var candidatesVisible = false

    lateinit var morsoGestureListener : MorsoGestureListener
    private val morsoUiState = MorsoUiState
    lateinit var morsoUiStateObserver: MorsoUiStateObserver



    /**
     * Create and return the view hierarchy used for the input area (such as
     * a soft keyboard).  This will be called once, when the input area is
     * first displayed.  You can return null to have no input area; the default
     * implementation returns null.
     *
     * <p>To control when the input view is displayed, implement
     * {@link #onEvaluateInputViewShown()}.
     * To change the input view after the first one is created by this
     * function, use {@link #setInputView(View)}.
     */
    override fun onCreateInputView(): View {
//        android.os.Debug.waitForDebugger()

        val morsoLayout = layoutInflater.inflate(R.layout.morso, null)
        morsoInputView = morsoLayout.findViewById<MorsoInputView>(R.id.morsoInputView)
        morsoGestureListener = morsoInputView.gestureListener
        morsoUiStateObserver = MorsoUiStateObserver(this, morsoUiState)

        setCandidatesViewShown(true)

        return morsoLayout
    }


    /**
     * Set Morso's GestureListener to the updated selection
     */
    override fun onUpdateSelection(
        oldSelStart: Int,
        oldSelEnd: Int,
        newSelStart: Int,
        newSelEnd: Int,
        candidatesStart: Int,
        candidatesEnd: Int
    ) {
        super.onUpdateSelection(
            oldSelStart,
            oldSelEnd,
            newSelStart,
            newSelEnd,
            candidatesStart,
            candidatesEnd
        )

        morsoGestureListener.inputConnection = currentInputConnection
    }

    override fun onCreateCandidatesView(): View {

        candidatesVisible = true

        candidatesLayout = layoutInflater.inflate(R.layout.candidates, null)

        return candidatesLayout
    }

    override fun onFinishCandidatesView(finishingInput: Boolean) {
        candidatesVisible = false
        super.onFinishCandidatesView(finishingInput)
    }

    /**
     * Called automatically from MorsoUiStateObserver whenever the state changes.
     */
    fun updateUi() {
        morsoInputView.updateUi(morsoUiState)

        if (candidatesVisible) {
            val current = candidatesLayout.findViewById<MorsoCandidateView>(R.id.morsoCurrentCandidate)
            val dot = candidatesLayout.findViewById<MorsoCandidateView>(R.id.morsoDotCandidate)
            val dash = candidatesLayout.findViewById<MorsoCandidateView>(R.id.morsoDashCandidate)

            current.text = morsoUiState.currentCandidateText.value
            dot.text = morsoUiState.dotCandidateText.value
            dash.text = morsoUiState.dashCandidateText.value


//            candidatesLayout.invalidate()
        }

    }

}