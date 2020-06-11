package com.example.androidsample.entity

import java.util.function.BinaryOperator
import java.util.function.DoubleBinaryOperator
import java.util.function.IntBinaryOperator

const val OPERATIONS_COUNT = 4

enum class Operations : BinaryOperator<Double>, DoubleBinaryOperator {
    ADD {
        override fun apply(t: Double, u: Double): Double = t + u

        override fun toString(): String = "+"
    },
    SUBTRACT {
        override fun apply(t: Double, u: Double): Double = t - u

        override fun toString(): String = "-"
    },
    MULTIPLY {
        override fun apply(t: Double, u: Double): Double = t * u

        override fun toString(): String = "×"
    },
    DIVIDE {
        override fun apply(t: Double, u: Double): Double = t / u

        override fun toString(): String = "÷"
    };

    override fun applyAsDouble(left: Double, right: Double): Double = apply(left, right)
}

fun getByIndex(index: Int): Operations {
    return when (index) {
        0 -> Operations.ADD
        1 -> Operations.SUBTRACT
        2 -> Operations.MULTIPLY
        3 -> Operations.DIVIDE
        else -> throw NotImplementedError("Only 4 operations supported yet")
    }
}