package net.eldun.morso

import android.inputmethodservice.InputMethodService
import android.util.Log
import android.view.View

class MorsoIME : InputMethodService() {
    private val TAG = "MorsoIME"

    override fun onCreateInputView(): View {
        return layoutInflater.inflate(R.layout.input_container, null)
    }
}