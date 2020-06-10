package com.example.androidsample.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.androidsample.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.playButton)
            .setOnClickListener(this)
        findViewById<Button>(R.id.scoresButton)
            .setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.playButton) {
            switchView(GameActivity::class.java)
        } else if (v?.id == R.id.scoresButton) {
            switchView(ScoreActivity::class.java)
        }
    }

    private fun switchView(clazz: Class<out AppCompatActivity>) {
        startActivity(
            Intent(
                this,
                clazz
            )
        )
    }
}
