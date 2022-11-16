package net.eldun.morso

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo


class MorsoIME : InputMethodService() {
    private val TAG = "MorsoIME"

    lateinit var morsoInputView: MorsoInputView
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
        morsoUiState = morsoGestureListener.morsoUiState
        morsoUiStateObserver = MorsoUiStateObserver(this, morsoUiState)


        return morsoLayout
    }



    /**
     * Called when the input view is being shown and input has started on
     * a new editor.  This will always be called after {@link #onStartInput},
     * allowing you to do your general setup there and just view-specific
     * setup here.  You are guaranteed that {@link #onCreateInputView()} will
     * have been called some time before this function is called.
     *
     * @param info Description of the type of text being edited.
     * @param restarting Set to true if we are restarting input on the
     * same text field as before.
     */
    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
    }


    fun updateUi() {
        morsoInputView.updateUi(morsoUiState)
    }

}