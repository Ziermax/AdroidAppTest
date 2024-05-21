package com.ziermax.pruebatest.todoapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ziermax.pruebatest.R
import com.ziermax.pruebatest.todoapp.TaskCategory.*

class ToDoListActivity : AppCompatActivity() {

	private val categories: List<TaskCategory> = listOf(Personal, Business, Other)
	private val tasks: MutableList<Task> = mutableListOf()

	private lateinit var currentTasks: MutableList<Task>
	private lateinit var rvCategories: RecyclerView
	private lateinit var categoriesAdapter: CategoriesAdapter

	private lateinit var rvTasks: RecyclerView
	private lateinit var tasksAdapter: TasksAdapter

	private lateinit var fabAddTask: FloatingActionButton

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
//		enableEdgeToEdge()
		currentTasks = tasks
		setContentView(R.layout.activity_to_do_list)
		initComponent()
		initListeners()
		initUi()
	}

	private fun initComponent() {
		rvCategories = findViewById(R.id.rvCategories)
		rvTasks = findViewById(R.id.rvTasks)
		fabAddTask = findViewById(R.id.fabAddTask)
	}

	private fun initListeners() {
		fabAddTask.setOnClickListener { showTaskDialog() }
	}

	private fun initUi() {
		categoriesAdapter = CategoriesAdapter(categories) { position -> updateCategories(position) }
		rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
		rvCategories.adapter = categoriesAdapter

		tasksAdapter = TasksAdapter(tasks) { position -> onItemSelected(position) }
		rvTasks.layoutManager = (LinearLayoutManager(this))
		rvTasks.adapter = tasksAdapter
	}

	private fun showTaskDialog() {
		val dialog = Dialog(this)
		dialog.setContentView(R.layout.dialog_add_task)

		val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
		val etTaskName: EditText = dialog.findViewById(R.id.etTaskName)
		val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

		btnAddTask.setOnClickListener {
			val taskName = etTaskName.text.toString()
			if (taskName.isNotEmpty()) {
				val selectedId = rgCategories.checkedRadioButtonId
				val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
				val currentCategory: TaskCategory = when (selectedRadioButton.text) {
					getString(R.string.todo_dialog_category_personal) -> Personal
					getString(R.string.todo_dialog_category_business) -> Business
					else -> Other
				}
				tasks.add(Task(taskName, currentCategory))
				updateTask()
				dialog.hide()
			}
		}
		dialog.show()
	}

	private fun updateCategories(position: Int) {
		categories[position].isSelected = !categories[position].isSelected
		categoriesAdapter.notifyItemChanged(position)
		updateTask()
	}

	private fun onItemSelected(position: Int) {
		currentTasks[position].isSelected = !currentTasks[position].isSelected
		updateTask()
	}

	private fun updateTask() {
		val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
		currentTasks = tasks.filter {
			selectedCategories.contains(it.category)
		}.toMutableList()
		tasksAdapter.tasks = currentTasks
		tasksAdapter.notifyDataSetChanged()
	}
}/*







		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars =
				insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(
				systemBars.left,
				systemBars.top,
				systemBars.right,
				systemBars.bottom
			)
			insets
		}*/