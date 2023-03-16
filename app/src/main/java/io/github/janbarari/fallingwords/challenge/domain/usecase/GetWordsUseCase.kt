package io.github.janbarari.fallingwords.challenge.domain.usecase

import io.github.janbarari.fallingwords.challenge.domain.entity.Word
import io.github.janbarari.fallingwords.challenge.domain.repository.WordsRepository
import io.github.janbarari.architecture.UseCaseNoInput
import javax.inject.Inject

class GetWordsUseCase @Inject constructor(
    private val repo: WordsRepository
) : UseCaseNoInput<List<Word>>() {
    override suspend fun execute(): List<Word> {
        val list = repo.getWords()

        return list.dropLast(288)
    }
}