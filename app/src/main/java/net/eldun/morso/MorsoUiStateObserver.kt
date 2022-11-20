package net.eldun.morso

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer

class MorsoUiStateObserver(val morso: MorsoIME, val uiState: MorsoUiState) {

    init {

        observeBackgroundText()
        observeCandidates()
    }

    private fun observeBackgroundText() {
        // Create the observer which updates the UI.
        val backgroundTextObserver = Observer<String> {

            morso.updateUi()

            if (uiState.backgroundText.value != "Morso") {
                Handler(Looper.getMainLooper()).postDelayed({
                    uiState.backgroundText.value = "Morso"
                }, 1000)
            }
        }

        // Observe the LiveData
        uiState.backgroundText.observeForever(backgroundTextObserver)
    }

    private fun observeCandidates() {

        // Create the observer which updates the UI.
        val candidatesTextObserver = Observer<String> {
            morso.updateUi()
        }

        // Observe the LiveData
        uiState.currentCandidateText.observeForever(candidatesTextObserver)
        uiState.dotCandidateText.observeForever(candidatesTextObserver)
        uiState.dashCandidateText.observeForever(candidatesTextObserver)
    }


}