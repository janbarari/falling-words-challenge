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
    ): Flow<ChallengeReducer> {
        return flow {
            val words = getWordsUseCase.execute()

            val word = words.random()
            val isTranslationCorrect: Boolean = Random().nextBoolean()
            val translation: String = if(isTranslationCorrect) {
                word.textSpa
            } else {
                words.filter { it.textEng != word.textEng }.random().textSpa
            }

            val currentWordState = CurrentWordState(
                word = word.textEng,
                translation = translation,
                isTranslationCorrect = isTranslationCorrect
            )

            emit(
                ChallengeReducer.WordsLoaded(
                    words,
                    currentWordState,
                    state.result
                )
            )
        }
    }
}
