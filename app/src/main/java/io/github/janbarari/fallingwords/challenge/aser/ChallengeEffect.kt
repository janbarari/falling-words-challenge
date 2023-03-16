package io.github.janbarari.fallingwords.challenge.aser

sealed class ChallengeEffect {
    object OnCorrectAnswerSelected: ChallengeEffect()
    object OnWrongAnswerSelected: ChallengeEffect()
    object Finish: ChallengeEffect()
}
