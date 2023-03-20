package io.github.janbarari.fallingwords.challenge.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.janbarari.fallingwords.challenge.aser.*
import io.github.janbarari.architecture.ActionHandler
import io.github.janbarari.architecture.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KClass

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    challengeEffectHandler: ChallengeEffectHandler,
    private val loadActionHandler: LoadActionHandler,
    private val checkAnswerActionHandler: CheckAnswerActionHandler,
    private val pickWordActionHandler: PickWordActionHandler
) : BaseViewModel<ChallengeAction, ChallengeState, ChallengeEffect, ChallengeReducer>(
    challengeEffectHandler,
    ChallengeState.Default
) {

    init {
        viewModelScope.launch {
            action(ChallengeAction.Load)
        }
    }

    override fun getActionHandlers(): Set<Pair<KClass<out ChallengeAction>, ActionHandler<*, ChallengeState, ChallengeEffect, ChallengeReducer>>> {
        return setOf(
            ChallengeAction.Load::class to loadActionHandler,
            ChallengeAction.ChooseAnswer::class to checkAnswerActionHandler,
            ChallengeAction.PickWord::class to pickWordActionHandler
        )
    }
}