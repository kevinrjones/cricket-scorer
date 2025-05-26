package com.knowledgespike.scorer.presentation.screens.listscorecards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.knowledgespike.scorer.presentation.components.ScorecardListItem
import androidx.compose.foundation.lazy.items

@Composable
fun SelectScorecardScreen(
    modifier: Modifier = Modifier,
    onAddOrEditScorecard: (Int?) -> Unit,
    onScore: (Int?) -> Unit,
    selectScorecardViewModel: SelectScorecardViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddOrEditScorecard(null) }) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_a_scorecard_descrirption)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
        ) {
            LazyColumn {
                items(selectScorecardViewModel.scorecards.value) {
                    ScorecardListItem(
                        scorecard = it,
                        onEdit = {
                            onAddOrEditScorecard(it)
                        },
                        onScore = onScore
                    )
                }
            }
        }
    }
}