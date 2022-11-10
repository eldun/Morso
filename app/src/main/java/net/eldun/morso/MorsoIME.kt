package net.eldun.morso

import android.inputmethodservice.InputMethodService
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer


class MorsoIME : InputMethodService() {
    private val TAG = "MorsoIME"

    lateinit var morsoView: MorsoView
    lateinit var morsoGestureListener : MorsoGestureListener
    lateinit var morsoUiState: MorsoUiState


    override fun onCreateInputView(): View {
//        android.os.Debug.waitForDebugger()

        val morsoLayout = layoutInflater.inflate(R.layout.input_container, null)
        morsoView = morsoLayout.findViewById<MorsoView>(R.id.morsoView)
        morsoGestureListener = morsoView.gestureListener
        morsoUiState = morsoGestureListener.morsoUiState


        // Create the observer which updates the UI.
        val backgroundTextObserver = Observer<String> {

            // Update the UI
            morsoView.updateUi(morsoUiState)
            morsoView.invalidate()

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