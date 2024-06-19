package com.bangkit.aktivio.modules.dietary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.aktivio.R

data class Meal(val name: String, val calories: String, val imageResId: Int)

class MealAdapter(private val meals: List<Meal>) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.mealName.text = meal.name
        holder.mealCalories.text = meal.calories
        holder.mealImage.setImageResource(meal.imageResId)
    }

    override fun getItemCount(): Int = meals.size

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealName: TextView = itemView.findViewById(R.id.tv_meal_name)
        val mealCalories: TextView = itemView.findViewById(R.id.tv_meal_calories)
        val mealImage: ImageView = itemView.findViewById(R.id.iv_meal_image)
    }
}
