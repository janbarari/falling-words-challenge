package io.github.janbarari.fallingwords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.janbarari.fallingwords.challenge.presentation.ChallengeViewModel
import io.github.janbarari.fallingwords.challenge.view.ChallengeScreen
import io.github.janbarari.fallingwords.intro.IntroScreen
import io.github.janbarari.fallingwords.score.ScoreScreen
import io.github.janbarari.fallingwords.score.state.ScoreViewState
import io.github.janbarari.fallingwords.theme.FallingWordsTheme

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberAnimatedNavController()
            val challengeViewModel: ChallengeViewModel = viewModel()
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
                        ChallengeScreen(navHostController, challengeViewModel)
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
