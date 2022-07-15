package space.mosk.therickandmorty.config

import com.squareup.moshi.Json

data class Character (
        @Json(name="name")
        val name: String,
        @Json(name="image")
        val image: String,
        @Json(name="status")
        val status: String,
        @Json(name="gender")
        val gender: String,
        @Json(name="species")
        val species: String,
        @Json(name="type")
        val type: String,
        @Json(name="origin")
        val origin: Origin,
        @Json(name="location")
        val location: Location,
        )
data class ResponseApi(@Json(name="results")
val result : List<Character>)