package io.github.janbarari.fallingwords.intro

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import io.github.janbarari.fallingwords.challenge.ChallengeScreen
import io.github.janbarari.fallingwords.theme.BrandColor

object IntroScreen {
    const val route: String = "intro"
}

@Composable
fun IntroScreen(navController: NavHostController) {
    IntroScreenContent(
        modifier = Modifier.fillMaxSize()
    ) {
        navController.navigate(ChallengeScreen.route) {
            //Skip Intro screen when back pressed
            popUpTo(IntroScreen.route) { inclusive = true }
        }
    }
}

@Composable
fun IntroScreenContent(
    modifier: Modifier,
    startOnClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_challenge),
            modifier = Modifier.size(74.dp),
            contentDescription = "Challenge Icon"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Play to Learn Spanish",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Get ready to show off your translation skills!Once the game starts, you'll have just 5 seconds to pick the right translation for each word.\n\nRemember, you can always skip a word if you're stumped, but it might impact your score.\n\nSo, channel your inner language ninja and give it your best shot!\n\nGood luck :D",
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
            onClick = startOnClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BrandColor,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(50),
        ) {
            Text(
                text = "Start Game",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

    }
}

@Composable
@Preview(device = Devices.PIXEL_4)
fun IntroScreenPreview() {
    IntroScreenContent(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

    }
}