package io.github.janbarari.fallingwords.score

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.janbarari.fallingwords.R
import io.github.janbarari.fallingwords.score.state.ScoreViewState

object ScoreScreen {
    const val route: String = "score?correct={correct}&wrong={wrong}&unanswered={unanswered}"
    fun generateRoute(correct: Int, wrong: Int, unanswered: Int): String {
        return "score?correct=$correct&wrong=$wrong&unanswered=$unanswered"
    }
}

@Composable
fun ScoreScreen(
    state: ScoreViewState
) {
    ScoreScreenContent(
        modifier = Modifier.fillMaxSize(),
        state = state
    )
}

@Composable
fun ScoreScreenContent(
    modifier: Modifier,
    state: ScoreViewState
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_score),
            modifier = Modifier.size(74.dp),
            contentDescription = "Score Icon"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Yaaay!!",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Thank you for taking the time to submit your answers. Your score is as follows:\n" +
                    "\nCorrect Answers: ${state.correctAnswers}" +
                    "\nIncorrect Answers: ${state.wrongAnswers}" +
                    "\nUnanswered: ${state.unanswered}" +
                    "\n" +
                    "\nIf you would like to improve your knowledge, we encourage you to try again. Best of luck!",
            fontFamily = FontFamily.Serif,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Justify,
            modifier = Modifier.width(316.dp)
        )
    }
}

@Composable
@Preview(device = Devices.PIXEL_4)
fun ScoreScreenPreview() {
    ScoreScreenContent(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        state = ScoreViewState(
            correctAnswers = 10,
            wrongAnswers = 12,
            unanswered = 1
        )
    )
}
