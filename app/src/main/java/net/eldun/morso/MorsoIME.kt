package net.eldun.morso

import android.inputmethodservice.InputMethodService
import android.view.View

class MorsoIME : InputMethodService() {

    override fun onCreateInputView(): View {
        return layoutInflater.inflate(R.layout.input, null).apply {
            if (this is MorsoView) {
                setOnKeyboardActionListener(this)
//                keyboard = latinKeyboard
            }
        }
    }

    private fun setOnKeyboardActionListener(action : MorsoIME) {

    }

}