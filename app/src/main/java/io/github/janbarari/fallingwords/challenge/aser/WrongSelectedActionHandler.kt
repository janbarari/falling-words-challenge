package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.architecture.ActionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WrongSelectedActionHandler @Inject constructor() :
    ActionHandler<ChallengeAction, ChallengeState, ChallengeEffect, ChallengeReducer> {
    override fun handle(
        action: ChallengeAction,
        state: ChallengeState,
        effect: suspend (ChallengeEffect) -> Unit
    ): Flow<ChallengeReducer> = flow {
        if (state.current?.isTranslationCorrect == false) {
            emit(ChallengeReducer.CorrectAnswerSelected)
            effect(ChallengeEffect.OnCorrectAnswerSelected)
        } else {
            emit(ChallengeReducer.WrongAnswerSelected)
            effect(ChallengeEffect.OnWrongAnswerSelected)
        }
    }
}
