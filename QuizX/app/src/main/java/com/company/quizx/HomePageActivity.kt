package com.company.quizx

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.quizx.Adapters.CategoryAdapter
import com.company.quizx.Models.CategoryModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomePageActivity : AppCompatActivity() {

    lateinit var pbCategory : ProgressBar
    lateinit var rvCategory : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        pbCategory = findViewById<ProgressBar>(R.id.pbCategory)
        pbCategory.visibility = View.VISIBLE
        rvCategory = findViewById<RecyclerView>(R.id.rvCategory)
        rvCategory.hasFixedSize()
        rvCategory.layoutManager = LinearLayoutManager(this)
        getCategoryData(rvCategory)
    }

    private fun getCategoryData(rvCategory: RecyclerView) {
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://opentdb.com/")
            .build()
            .create(InterfaceAPI :: class.java)

        val categoryData = retrofitBuilder.getCategoryData()

        categoryData.enqueue(object : Callback<CategoryModel?> {
            override fun onResponse(
                call: Call<CategoryModel?>,
                response: Response<CategoryModel?>
            ) {
                val categoryAdapter = CategoryAdapter(this@HomePageActivity ,response.body()!!.trivia_categories)
                pbCategory.visibility = View.GONE
                rvCategory.adapter = categoryAdapter
            }

            override fun onFailure(call: Call<CategoryModel?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}