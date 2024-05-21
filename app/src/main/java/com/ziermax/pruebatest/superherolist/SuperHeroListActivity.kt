package com.ziermax.pruebatest.superherolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziermax.pruebatest.R
import com.ziermax.pruebatest.databinding.ActivitySuperHeroListBinding
import com.ziermax.pruebatest.superherolist.DetailedHeroInfoActivity.Companion.EXTRA_HERO_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

	private lateinit var binding: ActivitySuperHeroListBinding
	private lateinit var retrofit: Retrofit
	private lateinit var adapter: SuperHeroAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
//		enableEdgeToEdge()
		binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
		setContentView(binding.root)
		retrofit = getRetrofit()
		initUi()

	}

	private fun initUi() {
		binding.svHeroSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {
				searchByName(query.orEmpty())
				return (false)
			}

			override fun onQueryTextChange(newText: String?): Boolean = false
		})

		adapter = SuperHeroAdapter() { heroId -> navigateDetailedHeroInfo(heroId) }
		binding.rvHeroes.setHasFixedSize(true)
		binding.rvHeroes.layoutManager = LinearLayoutManager(this)
		binding.rvHeroes.adapter = adapter
	}

	private fun searchByName(query: String) {
		binding.pbHeroSearchWait.isVisible = true
		CoroutineScope(Dispatchers.IO).launch {
			val myResponse: Response<SuperHeroDataResponse> =
				retrofit.create(ApiService::class.java).getSuperHeroes(query)
			if (myResponse.isSuccessful) {
				val response: SuperHeroDataResponse? = myResponse.body()
				if (response != null) {
					Log.i("TestApp", response.toString())
					runOnUiThread {
						adapter.updateList(response.heroes)
						binding.pbHeroSearchWait.isVisible = false
					}
				}
				Log.i("TestApp", "Funciona")
			} else {
				Log.i("TestApp", "No Funciona")
			}
		}
	}

	private fun getRetrofit(): Retrofit {
		return (Retrofit.Builder().baseUrl("https://superheroapi.com/api/")
			.addConverterFactory(GsonConverterFactory.create()).build())
	}

	private fun navigateDetailedHeroInfo(heroId: String) {
		val intent = Intent(this, DetailedHeroInfoActivity::class.java)
		intent.putExtra(EXTRA_HERO_ID, heroId)
		startActivity(intent)
	}
}/*







		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}*/