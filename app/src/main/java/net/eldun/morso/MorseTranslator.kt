package net.eldun.morso

import net.eldun.morso.enums.Character
import net.eldun.morso.enums.MorseSignal

class MorseTranslator {

    companion object {

        fun decode(vararg sequence: MorseSignal): Character? {

            return Character.fromSequenceList(sequence.asList())
        }

        fun encode() {

        }

    }
}