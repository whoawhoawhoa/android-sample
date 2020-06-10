package com.example.androidsample.activity

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidsample.R
import com.example.androidsample.entity.constraintByIndex
import com.example.androidsample.service.GameService
import com.example.androidsample.service.ScoresService

class GameActivity : AppCompatActivity(), View.OnClickListener {
    private val gameService: GameService = GameService()
    private val scoresService: ScoresService = ScoresService
    private var buttons: Array<Button> = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initButtons()
    }

    override fun onStart() {
        super.onStart()
        setButtonsVisibility()
        startGame()
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.menuButton) {
            toMenu()
            scoresService.setScore()
        } else {
            continueGame(
                buttons
                    .filter { unit ->
                        unit.visibility == View.VISIBLE
                    }
                    .indexOf(v)
            )
        }
    }

    private fun toMenu() {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        )
    }

    private fun startGame() {
        gameService.startGame()
        setupLabels()
    }

    private fun continueGame(index: Int) {
        // todo show toast
        val correctAnswer = gameService.answerGiven(index)
        if (correctAnswer) {
            scoresService.inc()
        }
        setButtonsVisibility()
        startGame()
    }

    private fun setupLabels() {
        findViewById<TextView>(R.id.leftNumber).text = gameService.getFirstNumber().toString()
        findViewById<TextView>(R.id.rightNumber).text = gameService.getSecondNumber().toString()
        findViewById<TextView>(R.id.operation).text = gameService.getOperation()
        setupButtonLabels()
    }

    private fun setupButtonLabels() {
        var index = 0
        buttons.forEach { button ->
            if (button.visibility != View.INVISIBLE) {
                button.text = gameService.getOptions()[index].toString()
                index++
            }
        }
    }

    private fun initButtons() {
        buttons = arrayOf(
            findViewById(R.id.answer1),
            findViewById(R.id.answer2),
            findViewById(R.id.answer3),
            findViewById(R.id.answer4),
            findViewById(R.id.answer5),
            findViewById(R.id.answer6)
        )
        buttons.forEach { unit ->
            unit.setOnClickListener(this)
        }
        findViewById<Button>(R.id.menuButton)
            .setOnClickListener(this)
    }

    private fun setButtonsVisibility() {
        buttons.forEachIndexed { i, unit ->
            val visible = constraintByIndex(i)(gameService.getLevel())
            if (!visible) {
                unit.visibility = View.INVISIBLE
            } else {
                unit.visibility = View.VISIBLE
            }
        }
    }
}
