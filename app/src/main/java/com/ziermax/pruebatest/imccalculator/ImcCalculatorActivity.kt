package com.ziermax.pruebatest.imccalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.ziermax.pruebatest.R
import com.ziermax.pruebatest.firstapp.FirstAppActivity
import org.w3c.dom.Text
import java.util.zip.DeflaterOutputStream

class ImcCalculatorActivity : AppCompatActivity() {

	private var isMaleSelected: Boolean = true
	private var isFemaleSelected: Boolean = false
	private var currentHeight: Int = 171
	private var currentWeight: Int = 86
	private var currentAge: Int = 22

	private lateinit var viewMale: CardView
	private lateinit var viewFemale: CardView
	private lateinit var tvHeight: TextView
	private lateinit var rsHeight: RangeSlider
	private lateinit var btnAddWeight: FloatingActionButton
	private lateinit var btnSubWeight: FloatingActionButton
	private lateinit var tvWeight: TextView
	private lateinit var btnAddAge: FloatingActionButton
	private lateinit var btnSubAge: FloatingActionButton
	private lateinit var tvAge: TextView
	private lateinit var btnCalculate: Button

	companion object{
		const val IMC_KEY = "IMC_RESULT"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
//		enableEdgeToEdge()
		setContentView(R.layout.activity_imc_calculator)

		initComponent()
		initListeners()
		initUI()
	}

	private fun initComponent() {
		viewMale = findViewById(R.id.viewMale)
		viewFemale = findViewById(R.id.viewFemale)
		tvHeight = findViewById(R.id.tvHeight)
		rsHeight = findViewById(R.id.rsHeight)
		btnAddWeight = findViewById(R.id.btnAddWeight)
		btnSubWeight = findViewById(R.id.btnSubWeight)
		tvWeight = findViewById(R.id.tvWeight)
		btnAddAge = findViewById(R.id.btnAddAge)
		btnSubAge = findViewById(R.id.btnSubAge)
		tvAge = findViewById(R.id.tvAge)
		btnCalculate = findViewById(R.id.btnCalculate)
	}

	private fun initListeners() {
		viewMale.setOnClickListener {
			if (!isMaleSelected) {
				toggleGenderColor()
				setGenderColor()
			}
		}
		viewFemale.setOnClickListener {
			if (!isFemaleSelected) {
				toggleGenderColor()
				setGenderColor()

			}
		}
		rsHeight.addOnChangeListener { _, value, _ ->
			currentHeight = value.toInt()
			setHeightText()
		}
		btnAddWeight.setOnClickListener {
			currentWeight += 1
			setWeightText()
		}
		btnSubWeight.setOnClickListener {
			currentWeight -= 1
			setWeightText()
		}
		btnAddAge.setOnClickListener {
			currentAge += 1
			setAgeText()
		}
		btnSubAge.setOnClickListener {
			currentAge -= 1
			setAgeText()
		}
		btnCalculate.setOnClickListener {
			val	result:Float = calculateImc()
			navigateToResultImc(result)
		}
	}

	private fun initUI() {
		setGenderColor()
		setHeightText()
		setWeightText()
		setAgeText()
	}

	private fun toggleGenderColor() {
		isMaleSelected = !isMaleSelected
		isFemaleSelected = !isFemaleSelected
	}

	private fun setGenderColor() {
		viewMale.setBackgroundColor(getBackGenderColor(isMaleSelected))
		viewFemale.setBackgroundColor(getBackGenderColor(isFemaleSelected))
	}

	private fun getBackGenderColor(isComponentSelected: Boolean): Int {

		val colorReference = if (isComponentSelected) {
			R.color.background_component_selected
		} else {
			R.color.background_component
		}
		return (ContextCompat.getColor(this, colorReference))
	}

	private fun setHeightText() {
		tvHeight.text = "${currentHeight.toString()} cm"
	}

	private fun setWeightText() {
		tvWeight.text = "${currentWeight.toString()} kg"
	}

	private fun setAgeText() {
		tvAge.text = "${currentAge.toString()}"
	}

	private fun calculateImc(): Float {
		var imc: Float =
			currentWeight / (currentHeight.toFloat() / 100 * currentHeight.toFloat() / 100)
		imc = (((imc - imc.toInt()) * 100).toInt()
			.toFloat() / 100 + imc.toInt()).toFloat()
//		Log.i("TagTest1", "El IMC2 es $imc")
		return (imc)
	}

	private fun navigateToResultImc(imc: Float) {
		val intent = Intent(this, ResultImcActivity::class.java)
		intent.putExtra(IMC_KEY, imc)
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