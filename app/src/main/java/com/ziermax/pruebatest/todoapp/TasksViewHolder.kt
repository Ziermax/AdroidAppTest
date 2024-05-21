package com.ziermax.pruebatest.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ziermax.pruebatest.R

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	private val tvTaskName: TextView = view.findViewById(R.id.tvTaskName)
	private val cbTask: CheckBox = view.findViewById(R.id.cbTask)

	fun render(task: Task) {
		tvTaskName.text = task.name

		if (task.isSelected)
			tvTaskName.paintFlags =
				tvTaskName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
		else
			tvTaskName.paintFlags =
				tvTaskName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

		cbTask.isChecked = task.isSelected
		val colorRef = when (task.category) {
			TaskCategory.Business -> R.color.todo_business_category
			TaskCategory.Other -> R.color.todo_other_category
			TaskCategory.Personal -> R.color.todo_personal_category
		}
		cbTask.buttonTintList = ColorStateList.valueOf(
			ContextCompat.getColor(cbTask.context, colorRef)
		)
	}
}