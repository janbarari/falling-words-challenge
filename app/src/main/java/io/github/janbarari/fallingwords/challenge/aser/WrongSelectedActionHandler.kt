package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.architecture.ActionHandler
import io.github.janbarari.fallingwords.challenge.presentation.state.QuestionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class WrongSelectedActionHandler @Inject constructor() :
    ActionHandler<ChallengeAction, ChallengeState, ChallengeEffect, ChallengeReducer> {
    override fun handle(
        action: ChallengeAction,
        state: ChallengeState,
        effect: suspend (ChallengeEffect) -> Unit
    ): Flow<ChallengeReducer> = flow {
        if (state.current?.isTranslationCorrect == false) {
            state.result.correct += 1
            emit(ChallengeReducer.CorrectAnswerSelected)
            effect(ChallengeEffect.OnCorrectAnswerSelected)
        } else {
            state.result.wrong += 1
            emit(ChallengeReducer.WrongAnswerSelected)
            effect(ChallengeEffect.OnWrongAnswerSelected)
        }
        if (state.result.answeredWords.size == state.words.size) {
            effect(ChallengeEffect.Finish)
        } else {
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
            state.result.answeredWords.add(questionState)
            emit(
                ChallengeReducer.UpdateQuestion(
                    questionState = questionState,
                    resultState = state.result
                )
            )
            effect(ChallengeEffect.StartTimer)
        }
    }
}
