package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.architecture.ActionHandler
import io.github.janbarari.fallingwords.challenge.presentation.state.CurrentWordState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class PickWordActionHandler @Inject constructor() :
    ActionHandler<ChallengeAction, ChallengeState, ChallengeEffect, ChallengeReducer> {
    override fun handle(
        action: ChallengeAction,
        state: ChallengeState,
        effect: suspend (ChallengeEffect) -> Unit
    ): Flow<ChallengeReducer> = flow {
        // Pick a new word
        val word = state.words.filter {
            state.result.answeredWords.any { answered -> answered.word == it.textEng }.not()
        }.random()
        val isTranslationCorrect: Boolean = Random().nextBoolean()
        val translation: String = if (isTranslationCorrect) {
            word.textSpa
        } else {
            state.words.filter {
                state.result.answeredWords.any { answered -> answered.word == it.textEng }
                    .not() &&
                        it.textEng != word.textEng
            }.random().textSpa
        }
        val currentWordState = CurrentWordState(
            word = word.textEng,
            translation = translation,
            isTranslationCorrect = isTranslationCorrect
        )
        val resultState = state.result
        resultState.answeredWords.add(state.current)
        resultState.unanswered += 1
        emit(
            ChallengeReducer.TimeUp(
                currentWordState = currentWordState,
                resultState = resultState
            )
        )
    }
}