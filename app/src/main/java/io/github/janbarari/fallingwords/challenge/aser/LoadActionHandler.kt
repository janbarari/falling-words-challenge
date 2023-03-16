package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.architecture.ActionHandler
import io.github.janbarari.fallingwords.challenge.domain.usecase.GetWordsUseCase
import io.github.janbarari.fallingwords.challenge.presentation.state.CurrentWordState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Random
import javax.inject.Inject

class LoadActionHandler @Inject constructor(
    private val getWordsUseCase: GetWordsUseCase
) : ActionHandler<ChallengeAction, ChallengeState, ChallengeEffect, ChallengeReducer> {
    override fun handle(
        action: ChallengeAction,
        state: ChallengeState,
        effect: suspend (ChallengeEffect) -> Unit
    ): Flow<ChallengeReducer> = flow {
        emit(ChallengeReducer.Loaded(getWordsUseCase.execute()))
    }
}
