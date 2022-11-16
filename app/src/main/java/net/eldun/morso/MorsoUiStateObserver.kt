package net.eldun.morso

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer

class MorsoUiStateObserver(morso: MorsoIME, uiState: MorsoUiState) {

    init {


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
}