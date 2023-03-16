package io.github.janbarari.fallingwords.challenge.aser

sealed class ChallengeAction {
    object LoadWords: ChallengeAction()
    object CorrectSelected: ChallengeAction()
    object WrongSelected: ChallengeAction()
}