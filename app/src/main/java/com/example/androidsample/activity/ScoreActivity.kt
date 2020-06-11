package com.example.androidsample.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import com.example.androidsample.R
import com.example.androidsample.service.ScoresService

class ScoreActivity : AppCompatActivity(), View.OnClickListener {
    private val scoresService: ScoresService = ScoresService
    private var textFields: Array<TextView> = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        initTextFields()
        findViewById<Button>(R.id.menuButton)
            .setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        )
    }

    override fun onStart() {
        super.onStart()
        setScores()
    }

    private fun initTextFields() {
        textFields = arrayOf(
            findViewById(R.id.top1),
            findViewById(R.id.top2),
            findViewById(R.id.top3),
            findViewById(R.id.top4),
            findViewById(R.id.top5)
        )
    }

    private fun setScores() {
        if (scoresService.getScores().size != 0) {
            findViewById<TextView>(R.id.noScoresText).visibility = INVISIBLE
        } else {
            findViewById<TextView>(R.id.noScoresText).visibility = VISIBLE
            return
        }
        scoresService
            .getScores()
            .forEachIndexed { index, unit ->
                if (index <= textFields.size) {
                    textFields[index].text = unit.toString()
                }
            }
    }
}
