package io.github.janbarari.fallingwords.challenge.presentation.state

data class QuestionState(
    val word: String,
    val translation: String,
    val isTranslationCorrect: Boolean
)