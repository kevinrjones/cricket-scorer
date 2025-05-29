package com.knowledgespike.scorer.presentation.screens.listscorecards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.knowledgespike.scorer.R
import com.knowledgespike.scorer.presentation.components.ScorecardListItem

@Composable
fun SelectScorecardScreen(
    modifier: Modifier = Modifier,
    onAddOrEditScorecard: (Int?) -> Unit,
    onScore: (Int?) -> Unit,
    selectScorecardViewModel: SelectScorecardViewModel = hiltViewModel(),
    isExpandedScreen: Boolean,
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

        val sizeFraction = if (!isExpandedScreen) {
            1f
        } else {
            0.5f
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(sizeFraction),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
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