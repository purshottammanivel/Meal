package com.purshottam.meal_search_application.presentation.meal_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.purshottam.meal_search_application.databinding.MealListItemBinding
import com.purshottam.meal_search_application.domain.model.Meal

class MealSearchAdapter : RecyclerView.Adapter<MealSearchAdapter.MealListItemViewHolder>() {

    private var mealList = mutableListOf<Meal>()
    private var onMealItemClick: ((Meal) -> Unit)? = null

    fun setMealList(meals: MutableList<Meal>) {
        this.mealList = meals
        notifyDataSetChanged()
    }

    class MealListItemViewHolder(
        val viewHolder: MealListItemBinding
    ) : RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealSearchAdapter.MealListItemViewHolder {
        val binding =
            MealListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealListItemViewHolder, position: Int) {
        holder.viewHolder.meal = this.mealList[position]
        holder.viewHolder.root.setOnClickListener {
            onMealItemClick?.let {
                it(this.mealList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    fun mealClickListener(mealClicked: (Meal) -> Unit) {
        onMealItemClick = mealClicked
    }
}