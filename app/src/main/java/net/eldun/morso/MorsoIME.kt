package net.eldun.morso

import android.inputmethodservice.InputMethodService
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer


class MorsoIME : InputMethodService() {
    private val TAG = "MorsoIME"

    lateinit var morsoInputView: MorsoInputView
    lateinit var morsoGestureListener : MorsoGestureListener
    lateinit var morsoUiState: MorsoUiState


    override fun onCreateInputView(): View {
//        android.os.Debug.waitForDebugger()

        val morsoLayout = layoutInflater.inflate(R.layout.morso, null)
        morsoInputView = morsoLayout.findViewById<MorsoInputView>(R.id.morsoInputView)
        morsoGestureListener = morsoInputView.gestureListener
        morsoUiState = morsoGestureListener.morsoUiState


        // Create the observer which updates the UI.
        val backgroundTextObserver = Observer<String> {

            // Update the UI
            morsoInputView.updateUi(morsoUiState)
            morsoInputView.invalidate()

            if (morsoUiState.backgroundText.value != "Morso") {
                Handler(Looper.getMainLooper()).postDelayed({
                    morsoUiState.backgroundText.value = "Morso"
                }, 1000)
            }
        }

        // Observe the LiveData
        morsoUiState.backgroundText.observeForever(backgroundTextObserver)

        return morsoLayout
    }

}