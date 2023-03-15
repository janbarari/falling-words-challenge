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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.github.janbarari.fallingwords.challenge.ChallengeScreen
import io.github.janbarari.fallingwords.intro.IntroScreen
import io.github.janbarari.fallingwords.score.ScoreScreen
import io.github.janbarari.fallingwords.score.state.ScoreViewState
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
                        IntroScreen(navHostController)
                    }
                    composable(
                        route = ChallengeScreen.route
                    ) {
                        ChallengeScreen(navHostController)
                    }
                    composable(
                        route = ScoreScreen.route,
                        arguments = listOf(
                            navArgument("correct") {
                                type = NavType.IntType
                            },
                            navArgument("wrong") {
                                type = NavType.IntType
                            },
                            navArgument("unanswered") {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        val correctAnswers = it.arguments?.getInt("correct")!!
                        val wrongAnswers = it.arguments?.getInt("wrong")!!
                        val unansweredQuestions = it.arguments?.getInt("unanswered")!!
                        ScoreScreen(
                            navHostController,
                            ScoreViewState(
                                correctAnswers,
                                wrongAnswers,
                                unansweredQuestions
                            )
                        )
                    }
                }
            }
        }
    }
}
