package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.architecture.ActionHandler
import io.github.janbarari.fallingwords.challenge.domain.usecase.GetWordsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Random
import javax.inject.Inject

class LoadWordsActionHandler @Inject constructor(
    private val getWordsUseCase: GetWordsUseCase
) : ActionHandler<ChallengeAction, ChallengeState, ChallengeEffect, ChallengeReducer> {
    override fun handle(
        action: ChallengeAction,
        state: ChallengeState,
        effect: suspend (ChallengeEffect) -> Unit
    ): Flow<ChallengeReducer> {
        return flow {
            val words = getWordsUseCase.execute()
            val word = words.random()
            val isAnswerCorrect: Boolean = Random().nextBoolean()
            val answer: String = if(isAnswerCorrect) {
                word.textSpa
            } else {
                words.filter { it.textEng != word.textEng }.random().textSpa
            }
            emit(
                ChallengeReducer.WordsLoaded(
                    words,
                    word.textEng,
                    answer,
                    isAnswerCorrect
                )
            )
        }
    }
}
