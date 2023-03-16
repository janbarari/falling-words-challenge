package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.architecture.Reducer
import io.github.janbarari.fallingwords.challenge.domain.entity.Word

sealed class ChallengeReducer(
    reducer: Reducer<ChallengeState>
) : Reducer<ChallengeState> by reducer {

    data class WordsLoaded(
        val words: List<Word>,
        val question: String,
        val answer: String,
        val isAnswerCorrect: Boolean
    ) : ChallengeReducer(
        {
            it.copy(
                words = words,
                question = question,
                answer = answer,
                isAnswerCorrect = isAnswerCorrect
            )
        }
    )

    object CorrectAnswerSelected : ChallengeReducer(
        {
            it.copy(
                answeredWords =
            )
        }
    )

    object WrongAnswerSelected : ChallengeReducer(
        {
            it.copy(

            )
        }
    )

}