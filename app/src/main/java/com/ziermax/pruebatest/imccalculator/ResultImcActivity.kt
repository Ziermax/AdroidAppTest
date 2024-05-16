package com.ziermax.pruebatest.imccalculator

import android.icu.number.Scale
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ziermax.pruebatest.R
import com.ziermax.pruebatest.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {

	private lateinit var tvResultScale: TextView
	private lateinit var tvImc: TextView
	private lateinit var tvDescription: TextView
	private lateinit var btnRecalculate: Button

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
//		enableEdgeToEdge()
		setContentView(R.layout.activity_result_imc)

		val imc: Float = intent.extras?.getFloat(IMC_KEY) ?: 0.0f

		initComponents()
		initUI(imc)
	}

	private fun	initComponents(){
		tvResultScale = findViewById(R.id.tvResultScale)
		tvImc = findViewById(R.id.tvImc)
		tvDescription = findViewById(R.id.tvDescription)
		btnRecalculate = findViewById(R.id.btnRecalculate)
	}

	private fun initListeners(){
		btnRecalculate.setOnClickListener { onBackPressedDispatcher.onBackPressed()}
	}

	private fun initUI(imc: Float) {
		tvImc.text = imc.toString()
		when (imc){
			in 0.0f..9.99f ->{
				tvResultScale.text = getString(R.string.title_vunder_imc)
				tvResultScale.setTextColor(ContextCompat.getColor(this, R.color.vunder_imc))
				tvDescription.text = getString(R.string.description_vunder_imc)
			}
			in 10.0f..18.5f ->{
				tvResultScale.text = getString(R.string.title_under_imc)
				tvResultScale.setTextColor(ContextCompat.getColor(this, R.color.under_imc))
				tvDescription.text = getString(R.string.description_under_imc)
			}
			in 18.51f..24.99f ->{
				tvResultScale.text = getString(R.string.title_normal_imc)
				tvResultScale.setTextColor(ContextCompat.getColor(this, R.color.normal_imc))
				tvDescription.text = getString(R.string.description_normal_imc)
			}
			in 25.99f..29.99f ->{
				tvResultScale.text = getString(R.string.title_over_imc)
				tvResultScale.setTextColor(ContextCompat.getColor(this, R.color.over_imc))
				tvDescription.text = getString(R.string.description_over_imc)
			}
			in 30.0f..50.0f ->{
				tvResultScale.text = getString(R.string.title_obesity_imc)
				tvResultScale.setTextColor(ContextCompat.getColor(this, R.color.obesity_imc))
				tvDescription.text = getString(R.string.description_obesity_imc)
			}
			else ->{
				tvImc.text = getString(R.string.error)
				tvResultScale.setTextColor(ContextCompat.getColor(this, R.color.obesity_imc))
				tvResultScale.text = getString(R.string.error)
				tvDescription.text = getString(R.string.error)
			}
		}
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