package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.architecture.ActionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CorrectSelectedActionHandler @Inject constructor() :
    ActionHandler<ChallengeAction, ChallengeState, ChallengeEffect, ChallengeReducer> {
    override fun handle(
        action: ChallengeAction,
        state: ChallengeState,
        effect: suspend (ChallengeEffect) -> Unit
    ): Flow<ChallengeReducer> = flow {
        if (state.isAnswerCorrect) {
            emit(ChallengeReducer.CorrectAnswerSelected)
            effect(ChallengeEffect.CorrectAnswerEffect)
        } else {
            emit(ChallengeReducer.WrongAnswerSelected)
            effect(ChallengeEffect.WrongAnswerEffect)
        }
    }
}
