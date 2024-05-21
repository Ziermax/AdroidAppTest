package com.ziermax.pruebatest.superherolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziermax.pruebatest.R

class SuperHeroAdapter(
	private var heroesList: List<SuperHeroItem> = emptyList(),
	private var navigateDetailedHeroInfo: (String) -> Unit
) :
	RecyclerView.Adapter<SuperHeroViewHolder>() {
	fun updateList(newList: List<SuperHeroItem>) {
		heroesList = newList
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
		val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
		return (SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_super_hero, parent, false)))
	}

	override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
		holder.bind(heroesList[position], navigateDetailedHeroInfo)
	}

	override fun getItemCount(): Int = heroesList.size

}