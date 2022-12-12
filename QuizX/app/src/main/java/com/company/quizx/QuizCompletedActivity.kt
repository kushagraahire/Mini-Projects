package com.company.quizx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizCompletedActivity : AppCompatActivity() {

    lateinit var tvRight : TextView
    lateinit var tvWrong : TextView
    lateinit var tvScore : TextView
    lateinit var btnHome : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_completed)

        val rightAnswers : String = intent.getStringExtra("rightAnswers").toString()
        val wrongAnswers : String = intent.getStringExtra("wrongAnswers").toString()
        val score : Int = Integer.parseInt(rightAnswers) * 10

        tvRight = findViewById(R.id.rightAnswers)
        tvWrong = findViewById(R.id.wrongAnswers)
        tvScore = findViewById(R.id.score)
        btnHome = findViewById(R.id.btnHome)

        tvRight.text = rightAnswers
        tvWrong.text = wrongAnswers
        tvScore.text = "$score Points"

        btnHome.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@QuizCompletedActivity , HomePageActivity :: class.java))
            finish()
        })
    }
}