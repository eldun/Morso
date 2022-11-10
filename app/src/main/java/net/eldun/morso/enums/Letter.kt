package net.eldun.morso.enums

import android.graphics.DashPathEffect
import net.eldun.morso.enums.MorseCharacter.*

enum class Letter(vararg morse: MorseCharacter) {

    A(DOT, DASH),
    B(DASH, DOT, DOT, DOT),
    C(DASH, DOT, DASH, DOT),
    D(DASH, DOT, DOT),
    E(DOT),
    F(DOT, DOT, DASH, DOT),
    G(DASH, DASH, DOT),
    H(DOT, DOT, DOT, DOT),
    I(DOT, DOT),
    J(DOT, DASH, DASH, DASH),
    K(DASH, DOT, DASH),
    L(DOT, DASH, DOT, DOT),
    M(DASH, DASH),
    N(DASH, DOT),
    O(DASH, DASH, DASH),
    P(DOT, DASH, DASH, DOT),
    Q(DASH, DASH, DOT, DASH),
    R(DOT, DASH, DOT),
    S(DOT, DOT, DOT),
    T(DASH),
    U(DOT, DOT, DASH),
    V(DOT, DOT, DOT, DASH),
    W(DOT, DASH, DASH),
    X(DASH, DOT, DOT, DASH),
    Y(DASH, DOT, DASH, DASH),
    Z(DASH, DASH, DOT, DOT)
}