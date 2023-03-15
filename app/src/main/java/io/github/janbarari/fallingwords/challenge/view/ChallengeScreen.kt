package io.github.janbarari.fallingwords.challenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.github.janbarari.fallingwords.intro.IntroScreen
import io.github.janbarari.fallingwords.score.ScoreScreen
import io.github.janbarari.fallingwords.theme.GreenColor

object ChallengeScreen {
    const val route: String = "challenge"
}

@Composable
fun ChallengeScreen(navHostController: NavHostController) {
    ChallengeScreenContent(
        modifier = Modifier.fillMaxSize(),
        title = "",
        word = "",
        translation = "",
        correctOnClick = {
            navHostController.navigate(
                route = ScoreScreen.generateRoute(10, 13, 99),
            ) {
                popUpTo(IntroScreen.route) { inclusive = true }
            }
        },
        wrongOnClick = {

        }
    )
}

@Composable
fun ChallengeScreenContent(
    modifier: Modifier,
    title: String,
    word: String,
    translation: String,
    correctOnClick: () -> Unit,
    wrongOnClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth(),
            color = GreenColor,
            progress = 0.7f
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = word,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = translation,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Button(
                onClick = wrongOnClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(end = 20.dp, start = 20.dp)
                    .width(120.dp)
            ) {
                Text(
                    text = "✘",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            Button(
                onClick = correctOnClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = GreenColor,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(end = 20.dp, start = 20.dp)
                    .width(120.dp)
            ) {
                Text(
                    text = "✓",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }

        }
    }
}

@Composable
@Preview(Devices.PIXEL_4)
fun ChallengeScreenPreview() {
    ChallengeScreenContent(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        title = "1/100",
        word = "primary school",
        translation = "escuela primaria",
        correctOnClick = {

        },
        wrongOnClick = {

        }
    )
}