package io.github.janbarari.fallingwords.score.state

data class ScoreViewState(
    val correctAnswers: Int,
    val wrongAnswers: Int,
    val unanswered: Int
)
