package io.github.janbarari.fallingwords.challenge.domain.repository

import io.github.janbarari.fallingwords.challenge.domain.entity.Word

interface WordsRepository {
    suspend fun getWords(): List<Word>
}
