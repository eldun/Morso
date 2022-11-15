package net.eldun.morso

import net.eldun.morso.enums.Character.*
import net.eldun.morso.enums.MorseSignal.*

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MorseTranslatorTest {
    @Test
    fun decoder_isCorrect() {

        assertEquals(E, MorseTranslator.decode(DOT))
        assertEquals(T, MorseTranslator.decode(DASH))
        assertEquals(C, MorseTranslator.decode(DASH, DOT, DASH, DOT))



    }
}