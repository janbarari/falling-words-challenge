package io.github.janbarari.fallingwords.score

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.navigation.NavHostController
import io.github.janbarari.fallingwords.R
import io.github.janbarari.fallingwords.intro.IntroScreen
import io.github.janbarari.fallingwords.score.state.ScoreViewState
import io.github.janbarari.fallingwords.theme.BrandColor

object ScoreScreen {
    const val route: String = "score"
}

@Composable
fun ScoreScreen(navHostController: NavHostController) {
    ScoreScreenContent(
        modifier = Modifier.fillMaxSize(),
        state = ScoreViewState(
            correctAnswers = 0,
            wrongAnswers = 0,
            unanswered = 0
        ),
        tryAgainOnClick = {
            navHostController.navigate(IntroScreen.route) {
                popUpTo(ScoreScreen.route) { inclusive = true }
            }
        }
    )
}

@Composable
fun ScoreScreenContent(
    modifier: Modifier,
    tryAgainOnClick: () -> Unit,
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
        Spacer(modifier = Modifier.height(76.dp))
        Button(
            onClick = tryAgainOnClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BrandColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50),
        ) {
            Text(
                text = "Try Again",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

    }
}

@Composable
@Preview(device = Devices.PIXEL_4)
fun ScoreScreenPreview() {
    ScoreScreenContent(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        tryAgainOnClick = {

        },
        state = ScoreViewState(
            correctAnswers = 10,
            wrongAnswers = 12,
            unanswered = 1
        )
    )
}