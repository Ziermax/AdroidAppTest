package com.ziermax.pruebatest.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziermax.pruebatest.R

class TasksAdapter(var tasks: List<Task>, private val onTaskSelected: (Int) -> Unit) :
	RecyclerView.Adapter<TasksViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false)
		return (TasksViewHolder(view))
	}

	override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
		holder.itemView.setOnClickListener { onTaskSelected(position) }
		holder.render(tasks[position])
	}

	override fun getItemCount() = tasks.size
}