package io.github.janbarari.fallingwords.challenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import io.github.janbarari.fallingwords.challenge.aser.ChallengeAction
import io.github.janbarari.fallingwords.challenge.aser.ChallengeEffect
import io.github.janbarari.fallingwords.challenge.presentation.ChallengeViewModel
import io.github.janbarari.fallingwords.intro.IntroScreen
import io.github.janbarari.fallingwords.score.ScoreScreen
import io.github.janbarari.fallingwords.theme.GreenColor
import io.github.janbarari.fallingwords.theme.RedColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

object ChallengeScreen {
    const val route: String = "challenge"
}

@Composable
fun ChallengeScreen(
    navHostController: NavHostController,
    viewModel: ChallengeViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()
    var backgroundColor by remember { mutableStateOf(Color.White) }

    LaunchedEffect(Unit) {
        viewModel.action(ChallengeAction.LoadWords)
        viewModel.effect.collectLatest {
            when (it) {
                is ChallengeEffect.CorrectAnswerEffect -> {
                    backgroundColor = GreenColor
                    delay(150)
                    backgroundColor = Color.White
                }
                is ChallengeEffect.WrongAnswerEffect -> {
                    backgroundColor = RedColor
                    delay(150)
                    backgroundColor = Color.White
                }
            }
        }
    }

    ChallengeScreenContent(
        modifier = Modifier
            .background(color = backgroundColor)
            .fillMaxSize(),
        title = "${state.answeredWords.size + 1}/${state.words.size - 1}",
        word = state.question,
        translation = state.answer,
        correctOnClick = {
            coroutineScope.launch {
                viewModel.action(ChallengeAction.CorrectSelected)
            }
        },
        wrongOnClick = {
            coroutineScope.launch {
                viewModel.action(ChallengeAction.WrongSelected)
            }
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