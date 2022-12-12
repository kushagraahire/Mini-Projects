package com.company.quizx.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.quizx.MainActivity
import com.company.quizx.Models.CategoryModel
import com.company.quizx.Models.TriviaCategory
import com.company.quizx.R
import com.company.quizx.SelectDifficultyActivity

class CategoryAdapter  (val context: Context, private var categoryList : List<TriviaCategory>) :
    RecyclerView.Adapter<CategoryAdapter.viewHolder>() {


    class viewHolder (itemView : View) : RecyclerView.ViewHolder (itemView){
        val tvCategoryName : TextView = itemView.findViewById(R.id.tvCategoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.tvCategoryName.text = categoryList[position].name
        holder.itemView.setOnClickListener {
            val id = categoryList[position].id
            val intent = Intent(context,SelectDifficultyActivity :: class.java)
            intent.putExtra("categoryID",id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}