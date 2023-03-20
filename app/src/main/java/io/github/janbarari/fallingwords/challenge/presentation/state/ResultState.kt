package io.github.janbarari.fallingwords.challenge.presentation.state

data class ResultState(
    var correct: Int = 0,
    var wrong: Int = 0,
    var unanswered: Int = 0,
    val askedWords: MutableList<QuestionState> = mutableListOf()
) {
    companion object {
        val Default = ResultState()
    }
}
