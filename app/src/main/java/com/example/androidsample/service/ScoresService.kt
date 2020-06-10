package com.example.androidsample.service

const val MAX_SCORES_COUNT = 5

object ScoresService {
    private val scores: MutableList<Int> = mutableListOf()
    private var currentScore: Int = 0

    fun inc() {
        currentScore++
    }

    fun setScore() {
        scores.add(currentScore)
        scores.sortDescending()
        if (scores.size >= MAX_SCORES_COUNT) {
            scores.dropLast(1)
        }
        currentScore = 0
    }

    fun getScores(): MutableList<Int> {
        return this.scores
    }
}