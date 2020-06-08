package com.example.androidsample.entity

const val MAX_NUMBER = 100
const val MAX_DEVIATION = 10
const val INITIAL_OPTIONS_SIZE = 3

data class Game(
    var firstNumber: Int = 0,
    var secondNumber: Int = 0,
    var operation: Operations = Operations.ADD,
    var answer: Int = 0,
    var options: Array<Int> = emptyArray(),
    // todo enum?
    var level: Levels = Levels.FIRST,
    var winStreak: Int = 0
)