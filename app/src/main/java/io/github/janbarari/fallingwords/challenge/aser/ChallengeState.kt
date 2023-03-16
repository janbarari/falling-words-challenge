package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.fallingwords.challenge.domain.entity.Word

data class ChallengeState(
    val words: List<Word> = emptyList(),
    val answeredWords: MutableList<Word> = mutableListOf(),
    val question: String = "",
    val answer: String = "",
    val isAnswerCorrect: Boolean = false,
    val correctAnswerCount: Int = 0,
    val wrongAnswerCount: Int = 0,
    val unansweredCount: Int = 0
) {
    companion object {
        val Default = ChallengeState()
    }
}
