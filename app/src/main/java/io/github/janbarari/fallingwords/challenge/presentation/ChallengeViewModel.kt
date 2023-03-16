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
    private val correctButtonClickedActionHandler: CorrectSelectedActionHandler,
    private val wrongButtonClickedActionHandler: WrongSelectedActionHandler,
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
            ChallengeAction.CorrectButtonClicked::class to correctButtonClickedActionHandler,
            ChallengeAction.WrongButtonClicked::class to wrongButtonClickedActionHandler,
            ChallengeAction.PickWord::class to pickWordActionHandler
        )
    }
}