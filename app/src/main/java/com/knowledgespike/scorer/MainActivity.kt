package com.knowledgespike.scorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.knowledgespike.scorer.presentation.AddEditScorecardDestination
import com.knowledgespike.scorer.presentation.ScorecardsListDestination
import com.knowledgespike.scorer.presentation.addeditscorecard.AddEditScorecardScreen
import com.knowledgespike.scorer.presentation.listscorecards.SelectScorecardScreen
import com.knowledgespike.scorer.ui.theme.ScorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScorerTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = ScorecardsListDestination
                    ) {
                        composable<ScorecardsListDestination> {
                            SelectScorecardScreen(modifier = Modifier.padding(innerPadding), onAddScorecard = {
                                navController.navigate(
                                    AddEditScorecardDestination(-1)
                                )
                            })
                        }

                        composable<AddEditScorecardDestination> {
                            AddEditScorecardScreen(modifier = Modifier, onSaveOrCancel = {})
                        }
                    }

                }
            }
        }
    }
}

