package io.github.janbarari.fallingwords.challenge.aser

sealed class ChallengeAction {
    object Load: ChallengeAction()
    data class PickWord(
        val isTimeUp: Boolean
    ): ChallengeAction()
    data class ChooseAnswer(
        val isCorrectSelected: Boolean = false,
        val isWrongSelected: Boolean = false,
        val isNoAnswer: Boolean = false
    ): ChallengeAction()
}