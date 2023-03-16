package io.github.janbarari.fallingwords.challenge.data

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.github.janbarari.fallingwords.challenge.data.source.AssetDataSource
import io.github.janbarari.fallingwords.challenge.domain.entity.Word
import io.github.janbarari.fallingwords.challenge.domain.repository.WordsRepository
import javax.inject.Inject

class WordsRepositoryImpl @Inject constructor(
    private val assetDataSource: AssetDataSource,
    private val moshi: Moshi
) : WordsRepository {
    override suspend fun getWords(): List<Word> {
        val file = assetDataSource.getFile("words.json")
        val adapter: JsonAdapter<List<Word>> = moshi.adapter(
            Types.newParameterizedType(List::class.java, Word::class.java)
        )
        val json = file.bufferedReader().use { it.readText() }
        return adapter.fromJson(json)!!
    }
}
