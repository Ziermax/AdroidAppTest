package com.ziermax.pruebatest

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ziermax.pruebatest.databinding.ActivityMenuBinding
import com.ziermax.pruebatest.firstapp.FirstAppActivity
import com.ziermax.pruebatest.imccalculator.ImcCalculatorActivity
import com.ziermax.pruebatest.settings.SettingsActivity
import com.ziermax.pruebatest.superherolist.SuperHeroListActivity
import com.ziermax.pruebatest.todoapp.ToDoListActivity

class MenuActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMenuBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
//		enableEdgeToEdge()
		binding = ActivityMenuBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.btnImcApp.setOnClickListener { navigateToImcApp() }
		binding.btnSaludApp.setOnClickListener { navigateToSaludApp() }
		binding.btnToDoList.setOnClickListener { navigateToToDoApp() }
		binding.btnHeroList.setOnClickListener { navigateToHeroListApp() }
		binding.btnSettings.setOnClickListener { navigateToSettings() }
	}

	private fun navigateToSettings() {
		val intent: Intent = Intent(this, SettingsActivity::class.java)
		startActivity(intent)
	}

	private fun navigateToHeroListApp() {
		val intent: Intent = Intent(this, SuperHeroListActivity::class.java)
		startActivity(intent)
	}

	private fun navigateToImcApp() {
		val intent: Intent = Intent(this, ImcCalculatorActivity::class.java)
		startActivity(intent)
	}

	private fun navigateToSaludApp(){
		val intent: Intent = Intent(this, FirstAppActivity::class.java)
		startActivity(intent)
	}

	private fun navigateToToDoApp() {
		val intent: Intent = Intent(this, ToDoListActivity::class.java)
		startActivity(intent)
	}
}
/*		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
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
