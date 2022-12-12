package com.company.quizx

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.company.quizx.Models.QuizModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizPageActivity : AppCompatActivity() {

    lateinit var tvCategoryQuiz : TextView
    lateinit var tvDifficultyQuiz : TextView
    lateinit var tvQueNo : TextView
    lateinit var tvQuestion : TextView
    lateinit var quizRG : RadioGroup
    lateinit var RadioA : RadioButton
    lateinit var RadioB : RadioButton
    lateinit var RadioC : RadioButton
    lateinit var RadioD : RadioButton
    lateinit var btnNext : Button

    var quizModel : QuizModel? = null
    var index : Int = 0
    var rightAnswers : Int = 0;
    var wrongAnswers : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_page)

        var difficulty : String = intent.getStringExtra("difficulty").toString()
        val categoryID : String = intent.getStringExtra("categoryID").toString()

        tvQuestion = findViewById(R.id.tvQuestion)
        tvQueNo = findViewById(R.id.tvQueNo)
        tvCategoryQuiz = findViewById(R.id.tvCategoryQuiz)
        tvDifficultyQuiz = findViewById(R.id.tvDifficultyQuiz)
        quizRG = findViewById(R.id.quizRG)
        RadioA = findViewById(R.id.RadioA)
        RadioB = findViewById(R.id.RadioB)
        RadioC = findViewById(R.id.RadioC)
        RadioD = findViewById(R.id.RadioD)
        btnNext = findViewById(R.id.btnNext)


        tvDifficultyQuiz.text = difficulty
        quizRG.visibility = View.INVISIBLE

        getQuizData(categoryID,difficulty)

    }

    override fun onBackPressed() {
        Toast.makeText(this,"Back Button is Disabled",Toast.LENGTH_SHORT)
    }

    private fun setQuiz(quizModel: QuizModel) {

        tvCategoryQuiz.text = this.quizModel!!.results[index].category
        tvQuestion.text = this.quizModel!!.results[index].question
        var queNo : Int = index + 1
        tvQueNo.text = "Question No : $queNo"
        setOptions()

    }

    private fun setOptions() {
        val random : Int = (1..4).random()

        if(random == 1){
            RadioA.text = quizModel!!.results[index].correct_answer
        }

        if(random == 2){
            RadioB.text = quizModel!!.results[index].correct_answer
        }

        if(random == 3){
            RadioC.text = quizModel!!.results[index].correct_answer
        }

        if(random == 4){
            RadioD.text = quizModel!!.results[index].correct_answer
        }

        var j : Int = 0;
        var k : Int = 1;

        while(j < 3){
            if(k == 1 && k != random){
                RadioA.text = quizModel!!.results[index].incorrect_answers[j]
                j++
            }

            if(k == 2 && k != random){
                RadioB.text = quizModel!!.results[index].incorrect_answers[j]
                j++
            }

            if(k == 3 && k != random){
                RadioC.text = quizModel!!.results[index].incorrect_answers[j]
                j++
            }

            if(k == 4 && k != random){
                RadioD.text = quizModel!!.results[index].incorrect_answers[j]
                j++
            }

            k++
        }

    }

    private fun getQuizData(categoryID: String, difficulty: String) {
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://opentdb.com/").build().create(InterfaceAPI ::class.java)

        val quizData = retrofitBuilder.getQuizData(10, categoryID, difficulty, "multiple")

        quizData.enqueue(object : Callback<QuizModel?> {
            override fun onResponse(call: Call<QuizModel?>, response: Response<QuizModel?>) {
                quizModel = response.body()!!
                quizRG.visibility = View.VISIBLE
                Log.e(TAG, quizModel!!.results[0].toString())
                setQuiz(quizModel!!)
                setEventListener( quizModel!!)
            }

            override fun onFailure(call: Call<QuizModel?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setEventListener(quizModel: QuizModel) {

        if(index < 10) {

            btnNext.setOnClickListener(View.OnClickListener {
                var ans: String? = null
                if (RadioA.isChecked) {
                    ans = RadioA.text.toString()
                } else if (RadioB.isChecked) {
                    ans = RadioB.text.toString()
                } else if (RadioC.isChecked) {
                    ans = RadioC.text.toString()
                } else if (RadioD.isChecked) {
                    ans = RadioD.text.toString()
                } else {
                    Toast.makeText(this, "You skipped a question", Toast.LENGTH_SHORT).show()
                }


                if(ans.equals(quizModel.results[index].correct_answer)){
                    rightAnswers++;
                }else{
                    wrongAnswers++;
                }

                index += 1

                if(index < 10){
                    setQuiz(quizModel)
                }else{
                    quizCompleted()
                }
                quizRG.clearCheck()
            })


        }else{
            quizCompleted()
        }

    }

    private fun quizCompleted(){
        var intent = Intent(this@QuizPageActivity, QuizCompletedActivity :: class.java)
        intent.putExtra("rightAnswers",rightAnswers.toString())
        intent.putExtra("wrongAnswers",wrongAnswers.toString())
        startActivity(intent)
        finish()
    }


}