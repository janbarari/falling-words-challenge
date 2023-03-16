package io.github.janbarari.fallingwords.challenge.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.janbarari.fallingwords.challenge.aser.*
import io.github.janbarari.architecture.ActionHandler
import io.github.janbarari.architecture.BaseViewModel
import javax.inject.Inject
import kotlin.reflect.KClass

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    challengeEffectHandler: ChallengeEffectHandler,
    private val loadWordsActionHandler: LoadWordsActionHandler,
    private val correctSelectedActionHandler: CorrectSelectedActionHandler,
    private val wrongSelectedActionHandler: WrongSelectedActionHandler
) : BaseViewModel<ChallengeAction, ChallengeState, ChallengeEffect, ChallengeReducer>(
    challengeEffectHandler,
    ChallengeState.Default
) {
    override fun getActionHandlers(): Set<Pair<KClass<out ChallengeAction>, ActionHandler<*, ChallengeState, ChallengeEffect, ChallengeReducer>>> {
        return setOf(
            ChallengeAction.LoadWords::class to loadWordsActionHandler,
            ChallengeAction.CorrectSelected::class to correctSelectedActionHandler,
            ChallengeAction.WrongSelected::class to wrongSelectedActionHandler
        )
    }
}