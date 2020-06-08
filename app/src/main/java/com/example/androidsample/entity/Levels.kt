package com.example.androidsample.entity

enum class Levels {
    FIRST {
        override fun index() = 0
        override fun maximumStreak(): Int = 5
    },
    SECOND {
        override fun index() = 1
        override fun maximumStreak(): Int = 10
    },
    THIRD {
        override fun index() = 2
        override fun maximumStreak(): Int = 15
    },
    FOURTH {
        override fun index() = 3
        override fun maximumStreak(): Int = 20
    };

    abstract fun index(): Int
    abstract fun maximumStreak(): Int
}

fun getByWinStreak(streak: Int): Levels {
    return when {
        Levels.FIRST.maximumStreak() < streak -> {
            Levels.FIRST
        }
        Levels.SECOND.maximumStreak() < streak -> {
            Levels.SECOND
        }
        Levels.THIRD.maximumStreak() < streak -> {
            Levels.THIRD
        }
        else -> {
            Levels.FOURTH
        }
    }
}