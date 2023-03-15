package io.github.janbarari.fallingwords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.github.janbarari.fallingwords.challenge.ChallengeScreen
import io.github.janbarari.fallingwords.intro.IntroScreen
import io.github.janbarari.fallingwords.score.ScoreScreen
import io.github.janbarari.fallingwords.theme.FallingWordsTheme

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberAnimatedNavController()
            FallingWordsTheme {
                AnimatedNavHost(
                    navController = navHostController,
                    startDestination = IntroScreen.route
                ) {
                    composable(
                        route = IntroScreen.route
                    ) {
                        IntroScreen()
                    }
                    composable(
                        route = ScoreScreen.route
                    ) {
                        ScoreScreen()
                    }
                    composable(
                        route = ChallengeScreen.route
                    ) {
                        ChallengeScreen()
                    }
                }
            }
        }
    }
}
