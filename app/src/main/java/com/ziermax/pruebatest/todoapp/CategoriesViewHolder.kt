package com.ziermax.pruebatest.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ziermax.pruebatest.R

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
	private val vDivider: View = view.findViewById(R.id.vDivider)
	private val viewContainer: CardView = view.findViewById(R.id.viewContainer)
	fun render(taskCategory: TaskCategory, updateCategories: (Int) -> Unit) {

		val colorRef = if (taskCategory.isSelected) {
			R.color.todo_background_card
		} else {
			R.color.todo_background_disabled
		}

		viewContainer.setCardBackgroundColor(
			ContextCompat.getColor(viewContainer.context, colorRef)
		)

		itemView.setOnClickListener { updateCategories(layoutPosition) }

		when (taskCategory) {
			TaskCategory.Personal -> {
				tvCategoryName.text = tvCategoryName.context.getString(R.string.todo_dialog_category_personal)
				vDivider.setBackgroundColor(
					ContextCompat.getColor(vDivider.context, R.color.todo_personal_category)
				)
			}

			TaskCategory.Business -> {
				tvCategoryName.text = tvCategoryName.context.getString(R.string.todo_dialog_category_business)
				vDivider.setBackgroundColor(
					ContextCompat.getColor(vDivider.context, R.color.todo_business_category)
				)
			}

			TaskCategory.Other -> {
				tvCategoryName.text = tvCategoryName.context.getString(R.string.todo_dialog_category_other)
				vDivider.setBackgroundColor(
					ContextCompat.getColor(vDivider.context, R.color.todo_other_category)
				)
			}
		}
	}
}