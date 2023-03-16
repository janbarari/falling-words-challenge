package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.architecture.ActionHandler
import io.github.janbarari.fallingwords.challenge.presentation.state.QuestionState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class PickWordActionHandler @Inject constructor() :
    ActionHandler<ChallengeAction.PickWord, ChallengeState, ChallengeEffect, ChallengeReducer> {
    override fun handle(
        action: ChallengeAction.PickWord,
        state: ChallengeState,
        effect: suspend (ChallengeEffect) -> Unit
    ): Flow<ChallengeReducer> = flow {
        // Pick a random unique word
        val word = state.words.filter {
            state.result.answeredWords.any { answered -> answered.word == it.textEng }.not()
        }.random()

        // Use random boolean to choose correct or wrong answer for the question
        val isTranslationCorrect: Boolean = Random().nextBoolean()
        // Assign the correct translation or pick from others
        val translation: String = if (isTranslationCorrect) {
            word.textSpa
        } else {
            state.words.filter {
                state.result.answeredWords.any { answered -> answered.word == it.textEng }.not()
                        && it.textEng != word.textEng
            }.random().textSpa
        }

        val questionState = QuestionState(
            word = word.textEng,
            translation = translation,
            isTranslationCorrect = isTranslationCorrect
        )

        emit(
            ChallengeReducer.UpdateQuestion(
                questionState = questionState,
                resultState = state.result
            )
        )
    }
}