package de.danielscholz.booleanSimplifier

import de.danielscholz.booleanSimplifier.expression.Expression

/**
 * Created by Daniel on 22.04.2017.
 */
class Rule @JvmOverloads constructor(
        val expr: Expression,
        val result: Expression,
        val isOptional: Boolean = false,
        val isMatchUnsharp: Boolean = false) {

    override fun toString(): String {
        return "$expr -> $result"
    }
}
