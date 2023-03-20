package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.architecture.ActionHandler
import io.github.janbarari.fallingwords.challenge.presentation.state.QuestionState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class CheckAnswerActionHandler @Inject constructor() :
    ActionHandler<ChallengeAction.ChooseAnswer, ChallengeState, ChallengeEffect, ChallengeReducer> {
    override fun handle(
        action: ChallengeAction.ChooseAnswer,
        state: ChallengeState,
        effect: suspend (ChallengeEffect) -> Unit
    ): Flow<ChallengeReducer> = flow {
        if (action.isCorrectSelected) {
            if (state.current?.isTranslationCorrect == true) {
                state.result.correct += 1
                emit(ChallengeReducer.CorrectAnswerSelected)
                effect(ChallengeEffect.OnCorrectAnswerSelected)
            } else {
                state.result.wrong += 1
                emit(ChallengeReducer.WrongAnswerSelected)
                effect(ChallengeEffect.OnWrongAnswerSelected)
            }
        } else if (action.isWrongSelected) {
            if (state.current?.isTranslationCorrect == true) {
                state.result.wrong += 1
                emit(ChallengeReducer.WrongAnswerSelected)
                effect(ChallengeEffect.OnWrongAnswerSelected)
            } else {
                state.result.correct += 1
                emit(ChallengeReducer.CorrectAnswerSelected)
                effect(ChallengeEffect.OnCorrectAnswerSelected)
            }
        } else if (action.isNoAnswer) {
            state.result.unanswered += 1
        }

        if (state.result.askedWords.size == state.words.size) {
            effect(ChallengeEffect.Finish)
        } else {
            // Pick a random unique word
            val word = state.words.filter { word ->
                state.result.askedWords.any { askedWord ->
                    askedWord.word == word.textEng
                }.not()
            }.random()
            // Use random boolean to choose correct or wrong answer for the question.
            val isTranslationCorrect: Boolean = Random().nextBoolean()
            // Assign the correct translation or pick from others.
            val translation: String = if (isTranslationCorrect) {
                word.textSpa
            } else {
                state.words.filter {
                    it.textEng != word.textEng
                }.random().textSpa
            }
            val questionState = QuestionState(
                word = word.textEng,
                translation = translation,
                isTranslationCorrect = isTranslationCorrect
            )
            state.result.askedWords.add(questionState)
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
