package net.eldun.morso

import androidx.lifecycle.MutableLiveData

class MorsoUiState() {



    val backgroundText: MutableLiveData<String> by lazy {
        MutableLiveData<String>("Morso")
    }
}