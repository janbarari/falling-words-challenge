package io.github.janbarari.fallingwords.challenge.aser

sealed class ChallengeAction {
    object Load: ChallengeAction()
    data class PickWord(
        val isTimeUp: Boolean
    ): ChallengeAction()
    object CorrectButtonClicked: ChallengeAction()
    object WrongButtonClicked: ChallengeAction()
}