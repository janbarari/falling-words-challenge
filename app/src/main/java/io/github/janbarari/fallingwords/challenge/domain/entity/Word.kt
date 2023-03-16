package io.github.janbarari.fallingwords.challenge.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Word(
    @Json(name = "text_eng")
    val textEng: String,
    @Json(name = "text_spa")
    val textSpa: String
)
