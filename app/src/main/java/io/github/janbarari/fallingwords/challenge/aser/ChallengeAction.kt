package io.github.janbarari.fallingwords.challenge.aser

sealed class ChallengeAction {
    object Load: ChallengeAction()
    object PickWord: ChallengeAction()
    object CorrectButtonClicked: ChallengeAction()
    object WrongButtonClicked: ChallengeAction()
}