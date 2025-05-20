package com.knowledgespike.scorer.presentation.listscorecards

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.knowledgespike.scorer.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SelectScorecardScreen(
    modifier: Modifier,
    onAddScorecard: () -> Unit,
    selectScorecardViewModel: SelectScorecardViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = onAddScorecard) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_a_scorecard_descrirption))
            }
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
        ) {

        }
    }
}