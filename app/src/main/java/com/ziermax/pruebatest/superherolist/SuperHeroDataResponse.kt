package com.ziermax.pruebatest.superherolist

import android.app.appsearch.StorageInfo
import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
	@SerializedName("response") val response: String,
	@SerializedName("results") val heroes: List<SuperHeroItem>
)

data class SuperHeroItem(
	@SerializedName("id") val heroId: String,
	@SerializedName("name") val heroName: String,
	@SerializedName("image") val heroImage: SuperHeroImage
)

data class SuperHeroImage(
	@SerializedName("url") val urlImage: String
)