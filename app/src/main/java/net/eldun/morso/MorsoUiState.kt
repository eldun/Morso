package net.eldun.morso

import androidx.lifecycle.MutableLiveData

object MorsoUiState {



    val backgroundText: MutableLiveData<String> by lazy {
        MutableLiveData<String>("Morso")
    }
}