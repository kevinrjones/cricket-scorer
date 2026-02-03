package com.knowledgespike.scorer

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.knowledgespike.scorer.presentation.ScorecardsListDestination
import com.knowledgespike.scorer.ui.theme.ScorerTheme

@Composable
fun ScorerApp(widthSizeClass: WindowWidthSizeClass) {
    ScorerTheme {
        val navController = rememberNavController()

        val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded

        ScoreAppNavGraph(
            navController,
            startDestination = ScorecardsListDestination,
            isExpandedScreen = isExpandedScreen
        )
    }

}


