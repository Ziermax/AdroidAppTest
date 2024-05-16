package com.ziermax.pruebatest

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ziermax.pruebatest.firstapp.FirstAppActivity
import com.ziermax.pruebatest.imccalculator.ImcCalculatorActivity

class MenuActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
//		enableEdgeToEdge()
		setContentView(R.layout.activity_menu)

		val btnSaludApp = findViewById<AppCompatButton>(R.id.btnSaludApp)
		val btnImcApp = findViewById<AppCompatButton>(R.id.btnImcApp)

		btnImcApp.setOnClickListener { navigateToImcApp() }
		btnSaludApp.setOnClickListener { navigateToSaludApp() }
	}

	private fun navigateToImcApp() {
		val intent = Intent(this, ImcCalculatorActivity::class.java)
		startActivity(intent)
	}

	private fun navigateToSaludApp(){
		val intent = Intent(this, FirstAppActivity::class.java)
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
