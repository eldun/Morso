package net.eldun.morso.enums

import android.util.Log
import net.eldun.morso.enums.MorseSignal.*

enum class Character(vararg var sequence: MorseSignal) {

    START() {
        override fun toString() = ""
        },

    E(DOT),
    T(DASH),

    I(DOT, DOT),
    A(DOT, DASH),
    N(DASH, DOT),
    M(DASH, DASH),

    S(DOT, DOT, DOT),
    U(DOT, DOT, DASH),
    R(DOT, DASH, DOT),
    W(DOT, DASH, DASH),
    D(DASH, DOT, DOT),
    K(DASH, DOT, DASH),
    G(DASH, DASH, DOT),
    O(DASH, DASH, DASH),

    H(DOT, DOT, DOT, DOT),
    V(DOT, DOT, DOT, DASH),
    F(DOT, DOT, DASH, DOT),
    L(DOT, DASH, DOT, DOT),
    P(DOT, DASH, DASH, DOT),
    J(DOT, DASH, DASH, DASH),
    B(DASH, DOT, DOT, DOT),
    X(DASH, DOT, DOT, DASH),
    C(DASH, DOT, DASH, DOT),
    Y(DASH, DOT, DASH, DASH),
    Z(DASH, DASH, DOT, DOT),
    Q(DASH, DASH, DOT, DASH),

    FIVE(DOT, DOT, DOT, DOT, DOT) {
        override fun toString() = "5"
    },
    FOUR(DOT, DOT, DOT, DOT, DASH){
        override fun toString() = "4"
    },
    THREE(DOT, DOT, DOT, DASH, DASH){
        override fun toString() = "3"
    },
    TWO(DOT, DOT, DASH, DASH, DASH){
        override fun toString() = "2"
    },
    PLUS_SIGN(DOT, DASH, DOT, DASH, DOT){
        override fun toString() = "+"
    },
    ONE(DOT, DASH, DASH, DASH, DASH){
        override fun toString() = "1"
    },
    SIX(DASH, DOT, DOT, DOT, DOT){
        override fun toString() = "6"
    },
    EQUALS_SIGN(DASH, DOT, DOT, DOT, DASH){
        override fun toString() = "="
    },
    DIVIDE_SIGN(DASH, DOT, DOT, DASH, DOT){
        override fun toString() = "/"
    },
    SEVEN(DASH, DASH, DOT, DOT, DOT){
        override fun toString() = "7"
    },
    EIGHT(DASH, DASH, DASH, DOT, DOT){
        override fun toString() = "8"
    },
    NINE(DASH, DASH, DASH, DASH, DOT){
        override fun toString() = "9"
    },
    ZERO(DASH, DASH, DASH, DASH, DASH){
        override fun toString() = "0"
    },
    NULL(){
        override fun toString() = ""
    };

    private val sequenceList = this.sequence.asList()


    companion object {
        val TAG = "Character"

        private val sequenceMap = values().associateBy(Character::sequenceList)
        private val stringMap = values().associateBy(Character::toString)

        fun fromSequenceList(seqList: List<MorseSignal>) = sequenceMap[seqList]
        fun fromString(stringifiedCharacter: String) = stringMap[stringifiedCharacter]

        fun getDotChild(character: Character): Character {
            val result = fromSequenceList(character.sequenceList + DOT)
            if (result == null)
                return Character.NULL
            return result
        }

        fun getDotChild(characterString: String?): Character {
            val character = fromString(characterString!!)
            return getDotChild(character!!)
        }


        fun getDashChild(character: Character): Character {
            val result = fromSequenceList(character.sequenceList + DASH)
            if (result == null)
                return Character.NULL
            return result
        }

        fun getDashChild(characterString: String?): Character {
            val character = fromString(characterString!!)
            return getDashChild(character!!)
        }
    }
}

