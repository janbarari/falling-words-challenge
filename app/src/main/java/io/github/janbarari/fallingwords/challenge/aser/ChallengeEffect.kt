package io.github.janbarari.fallingwords.challenge.aser

sealed class ChallengeEffect {
    object CorrectAnswerEffect: ChallengeEffect()
    object WrongAnswerEffect: ChallengeEffect()
}
