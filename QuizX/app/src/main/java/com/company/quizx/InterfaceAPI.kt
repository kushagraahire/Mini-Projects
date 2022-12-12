package com.company.quizx


import com.company.quizx.Models.CategoryModel
import com.company.quizx.Models.QuizModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface InterfaceAPI {

    @GET("api_category.php")
    fun getCategoryData() : Call<CategoryModel>

    @GET("api.php")
    fun getQuizData(@Query("amount") amount : Int,
                    @Query("category") category: String,
                    @Query("difficulty") difficulty : String,
                    @Query("type") type : String) : Call<QuizModel>
}