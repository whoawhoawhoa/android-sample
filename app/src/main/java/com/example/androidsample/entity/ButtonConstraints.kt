package com.example.androidsample.entity

fun constraintByIndex(index: Int): (Levels) -> Boolean {
    return when (index) {
        0, 2 -> ::twoLastLevelsAvailable
        1 -> ::secondAndLastLevelsAvailable
        else -> ::allLevelsAvailable
    }
}

fun twoLastLevelsAvailable(level: Levels): Boolean {
    return level.index() > 1
}

fun secondAndLastLevelsAvailable(level: Levels): Boolean {
    return level.index() == 1 || level.index() == 3
}

fun allLevelsAvailable(levels: Levels): Boolean = true