package io.github.janbarari.fallingwords.challenge.aser

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.janbarari.fallingwords.R
import io.github.janbarari.architecture.EffectHandler
import javax.inject.Inject

class ChallengeEffectHandler @Inject constructor(
    @ApplicationContext private val context: Context
) : EffectHandler<ChallengeEffect>() {
    override suspend fun handleEffect(effect: ChallengeEffect) {
        when (effect) {
            ChallengeEffect.OnCorrectAnswerSelected -> {
                MediaPlayer.create(context, R.raw.correct).start()
                effects.emit(ChallengeEffect.OnCorrectAnswerSelected)
            }
            ChallengeEffect.OnWrongAnswerSelected -> {
                MediaPlayer.create(context, R.raw.wrong).start()
                effects.emit(ChallengeEffect.OnWrongAnswerSelected)
            }
            ChallengeEffect.Finish -> {
                effects.emit(ChallengeEffect.Finish)
            }
            ChallengeEffect.StartTimer -> {
                effects.emit(ChallengeEffect.StartTimer)
            }
        }
    }
}
