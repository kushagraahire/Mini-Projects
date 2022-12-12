package com.company.quizx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView

class SelectDifficultyActivity : AppCompatActivity() {

    lateinit var cvEasy : CardView
    lateinit var cvMedium : CardView
    lateinit var cvHard : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_difficulty)

        val categoryID : String = intent.getIntExtra("categoryID",99).toString()
        cvEasy = findViewById(R.id.cvEasy)
        cvMedium = findViewById(R.id.cvMedium)
        cvHard = findViewById(R.id.cvHard)

        val intent = Intent(this@SelectDifficultyActivity,QuizPageActivity :: class.java)
        cvEasy.setOnClickListener(View.OnClickListener {
            setDifficulty(intent,"easy",categoryID)
        })

        cvMedium.setOnClickListener(View.OnClickListener {
            setDifficulty(intent,"medium",categoryID)
        })

        cvHard.setOnClickListener(View.OnClickListener {
            setDifficulty(intent,"hard",categoryID)
        })
    }

    private fun setDifficulty(intent: Intent, difficulty: String, categoryID: String) {
        intent.putExtra("difficulty",difficulty)
        intent.putExtra("categoryID",categoryID)
        startActivity(intent)
    }


}