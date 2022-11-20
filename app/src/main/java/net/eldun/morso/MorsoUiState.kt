package net.eldun.morso

import androidx.lifecycle.MutableLiveData
import net.eldun.morso.enums.Character

// Singleton
object MorsoUiState {

    val DEFAULT_BACKGROUND_TEXT = "Morso"

    val backgroundText: MutableLiveData<String> by lazy {
        MutableLiveData<String>(DEFAULT_BACKGROUND_TEXT)
    }

    // Default characters
    val currentCandidateText: MutableLiveData<String> by lazy {
            MutableLiveData<String>(Character.START.toString())
        }
    val dotCandidateText: MutableLiveData<String> by lazy {
        MutableLiveData<String>(Character.E.toString())
    }
    val dashCandidateText: MutableLiveData<String> by lazy {
        MutableLiveData<String>(Character.T.toString())
    }

    fun reset() {
        backgroundText.value = DEFAULT_BACKGROUND_TEXT
        currentCandidateText.value = Character.START.toString()
        dotCandidateText.value = Character.E.toString()
        dashCandidateText.value = Character.T.toString()
    }
}