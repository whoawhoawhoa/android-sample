package com.example.androidsample.service

import com.example.androidsample.entity.*
import kotlin.math.floor
import kotlin.random.Random

// todo maybe this should be obj?
class GameService {
    private val game: Game = Game()

    private val random: Random = Random(1)

    fun startGame() {
        setRandomNumbers()
        setOperation()
        setOptions()
    }

    fun answerGiven(index: Int): Boolean {
        val answerCorrect = game.options[index] == game.answer
        if (answerCorrect) {
            game.winStreak++
        } else {
            game.winStreak = 0
        }
        game.level = getByWinStreak(game.winStreak)
        return answerCorrect
    }

    fun getOptions(): Array<Int> {
        return game.options
    }

    fun getFirstNumber(): Int {
        return game.firstNumber
    }

    fun getSecondNumber(): Int {
        return game.secondNumber
    }

    fun getOperation(): String {
        return game.operation.toString()
    }

    fun getLevel(): Levels {
        return game.level
    }

    private fun setRandomNumbers() {
        game.firstNumber = random.nextInt(MAX_NUMBER)
        game.secondNumber = random.nextInt(MAX_NUMBER)
    }

    private fun setOperation() {
        var settled = false
        while (!settled) {
            val index = random.nextInt(OPERATIONS_COUNT)
            val operation = getByIndex(index)
            val res = operation.apply(game.firstNumber.toDouble(), game.secondNumber.toDouble())
            if (floor(res) == res) {
                game.operation = operation
                game.answer = res.toInt()
                settled = true
            }
        }
    }

    private fun setOptions() {
        initOptions()
        val answerIndex = setAnswerToOptions()
        game.options.forEachIndexed { i, _ ->
            if (answerIndex == i) {
                return@forEachIndexed
            }
            setOption(i)
        }
    }

    private fun setOption(index: Int) {
        var settled = false
        while (!settled) {
            val option = game.answer + random.nextInt(2 * MAX_DEVIATION) - MAX_DEVIATION
            if (option != game.answer) {
                game.options[index] = option
                settled = true
            }
        }
    }

    private fun setAnswerToOptions(): Int {
        val index = random.nextInt(game.options.size)
        game.options[index] = game.answer
        return index
    }

    private fun initOptions() {
        game.options = Array(INITIAL_OPTIONS_SIZE + game.level.index()) { 0 }
    }
}