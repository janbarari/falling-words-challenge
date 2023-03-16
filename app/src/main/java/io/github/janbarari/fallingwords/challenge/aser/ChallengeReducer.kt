package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.architecture.Reducer
import io.github.janbarari.fallingwords.challenge.domain.entity.Word
import io.github.janbarari.fallingwords.challenge.presentation.state.QuestionState
import io.github.janbarari.fallingwords.challenge.presentation.state.ResultState

sealed class ChallengeReducer(
    reducer: Reducer<ChallengeState>
) : Reducer<ChallengeState> by reducer {

    data class Loaded(
        val words: List<Word>
    ) : ChallengeReducer(
        {
            it.copy(words = words)
        }
    )

    data class UpdateQuestion(
        val questionState: QuestionState
    ): ChallengeReducer(
        {
            it.copy(
                current = questionState
            )
        }
    )

    object CorrectAnswerSelected : ChallengeReducer(
        {
            it.copy(

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