package net.eldun.morso

import androidx.lifecycle.MutableLiveData

// Singleton
object MorsoUiState {



    val backgroundText: MutableLiveData<String> by lazy {
        MutableLiveData<String>("Morso")
    }
}