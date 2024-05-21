package com.ziermax.pruebatest.superherolist

import com.google.gson.annotations.SerializedName

data class HeroDetailedResponse(
	@SerializedName("name") val heroName: String,
	@SerializedName("powerstats") val powerStats: PowerStats,
	@SerializedName("image") val heroImageDetailed: DetailedHeroImage,
	@SerializedName("biography") val heroBiography: DetailedHeroBio
)

data class PowerStats(
	@SerializedName("intelligence") val intelligence: String,
	@SerializedName("strength") val strength: String,
	@SerializedName("speed") val speed: String,
	@SerializedName("durability") val durability: String,
	@SerializedName("power") val power: String,
	@SerializedName("combat") val combat: String
)

data class DetailedHeroImage(
	@SerializedName("url") val urlImage: String
)

data class DetailedHeroBio(
	@SerializedName("full-name") val heroFullName: String,
	@SerializedName("publisher") val heroPublisher: String
)