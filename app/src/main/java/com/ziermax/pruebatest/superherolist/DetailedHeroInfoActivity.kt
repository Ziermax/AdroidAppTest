package com.ziermax.pruebatest.superherolist

import android.app.appsearch.StorageInfo
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.ziermax.pruebatest.R
import com.ziermax.pruebatest.databinding.ActivityDetailedHeroInfoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.roundToInt

class DetailedHeroInfoActivity : AppCompatActivity() {

	companion object {
		const val EXTRA_HERO_ID = "extra_id"
	}

	private lateinit var binding: ActivityDetailedHeroInfoBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
//		enableEdgeToEdge()
		binding = ActivityDetailedHeroInfoBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val heroId: String = intent.getStringExtra(EXTRA_HERO_ID).orEmpty()
		getHeroInfoById(heroId)
	}

	private fun getHeroInfoById(heroId: String) {
		CoroutineScope(Dispatchers.IO).launch {
			val superHeroDetail: Response<HeroDetailedResponse> =
				getRetrofit().create(ApiService::class.java).getHeroDetailedInfo(heroId)
			if (superHeroDetail.body() != null) {
				runOnUiThread { createUi(superHeroDetail.body()!!) }
			}
		}
	}

	private fun createUi(heroDetailedInfo: HeroDetailedResponse) {
		Picasso.get().load(heroDetailedInfo.heroImageDetailed.urlImage).into(binding.ivHeroDetailImage)
		binding.tvHeroDetailName.text = heroDetailedInfo.heroName
		prepareStats(heroDetailedInfo.powerStats)
		binding.tvHeroFullName.text = heroDetailedInfo.heroBiography.heroFullName
		binding.tvHeroPublisher.text = heroDetailedInfo.heroBiography.heroPublisher
	}

	private fun prepareStats(heroStats: PowerStats) {
		updateHeightStat(binding.viewCombat, heroStats.combat)
		updateHeightStat(binding.viewIntelligence, heroStats.intelligence)
		updateHeightStat(binding.viewPower, heroStats.power)
		updateHeightStat(binding.viewSpeed, heroStats.speed)
		updateHeightStat(binding.viewDurability, heroStats.durability)
		updateHeightStat(binding.viewStrength, heroStats.strength)
	}

	private fun updateHeightStat(view: View, newHeight: String) {
		val params = view.layoutParams
		params.height = pxToDp(newHeight.toFloat())
		view.layoutParams = params
	}

	private fun pxToDp(px: Float): Int {
		return (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt())
	}

	private fun getRetrofit(): Retrofit {
		return (Retrofit.Builder().baseUrl("https://superheroapi.com/api/")
			.addConverterFactory(GsonConverterFactory.create()).build())
	}
}
/*





		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}*/