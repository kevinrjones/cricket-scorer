package com.knowledgespike.scorer

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.knowledgespike.scorer.presentation.ScoreMatchDestination
import com.knowledgespike.scorer.presentation.AddEditScorecardDestination
import com.knowledgespike.scorer.presentation.ScorecardsListDestination
import com.knowledgespike.scorer.presentation.ScorerDestination
import com.knowledgespike.scorer.presentation.screens.addeditscorecard.AddEditScorecardScreen
import com.knowledgespike.scorer.presentation.screens.listscorecards.SelectScorecardScreen
import com.knowledgespike.scorer.presentation.screens.scorematch.ScoreMatchScreen

@Composable
fun ScoreAppNavGraph(
    navController: NavHostController = rememberNavController(),
    isExpandedScreen: Boolean = false,
    startDestination: ScorerDestination = ScorecardsListDestination,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<ScorecardsListDestination> {
            SelectScorecardScreen(
                isExpandedScreen = isExpandedScreen,
                onAddOrEditScorecard = {
                    navController.navigate(
                        AddEditScorecardDestination(it)
                    )
                },
                onScore = {
                    navController.navigate(
                        ScoreMatchDestination(it)
                    )
                })
        }

        composable<AddEditScorecardDestination> {
            AddEditScorecardScreen(
                isExpandedScreen = isExpandedScreen,
                onSaveOrCancel = {
                    navController.popBackStack()
                }
            )
        }

        composable<ScoreMatchDestination> {
            ScoreMatchScreen(
                isExpandedScreen = isExpandedScreen,
                onCloseScreen = {
                    navController.popBackStack()
                }
            )
        }
    }
}