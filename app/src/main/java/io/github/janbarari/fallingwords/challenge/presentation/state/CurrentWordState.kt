package io.github.janbarari.fallingwords.challenge.presentation.state

data class CurrentWordState(
    val word: String = "",
    val translation: String = "",
    val isTranslationCorrect: Boolean = false
) {
    companion object {
        val Default = CurrentWordState()
    }
}
