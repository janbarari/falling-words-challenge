package io.github.janbarari.fallingwords.challenge.aser

import io.github.janbarari.fallingwords.challenge.domain.entity.Word
import io.github.janbarari.fallingwords.challenge.presentation.state.ResultState
import io.github.janbarari.fallingwords.challenge.presentation.state.CurrentWordState

data class ChallengeState(
    val words: List<Word> = emptyList(),
    val current: CurrentWordState = CurrentWordState.Default,
    val result: ResultState = ResultState.Default
) {
    companion object {
        val Default = ChallengeState()
    }
}
