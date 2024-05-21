package com.ziermax.pruebatest.superherolist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ziermax.pruebatest.databinding.ItemSuperHeroBinding

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	private val binding: ItemSuperHeroBinding = ItemSuperHeroBinding.bind(view)

	fun bind(superHeroItem: SuperHeroItem, navigateHeroItem: (String) -> Unit) {
		binding.tvHeroName.text = superHeroItem.heroName
		Picasso.get().load(superHeroItem.heroImage.urlImage).into(binding.ivHeroImage)
		binding.root.setOnClickListener { navigateHeroItem(superHeroItem.heroId) }
	}
}