package com.knowledgespike.scorer.presentation.addeditscorecard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.knowledgespike.scorer.R
import com.knowledgespike.scorer.ui.theme.ScorerTheme
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditScorecardScreen(
    modifier: Modifier,
    onSaveOrCancel: () -> Unit,
    viewModel: AddEditScorecardViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val savedMessage = stringResource(R.string.saved_scorecard)
    val unableToSaveScorecardMessage = stringResource(R.string.unable_to_save_scorecard)

    LaunchedEffect(true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                AddEditScorecardEvent.SavedScorecard -> {
                    snackbarHostState.showSnackbar(message = savedMessage)
                    onSaveOrCancel()
                }

                is AddEditScorecardEvent.ErrorSavingScorecard -> {
                    snackbarHostState.showSnackbar(message = unableToSaveScorecardMessage)
                }

            }

        }
    }

    val scorecard = viewModel.scorecard.value

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        Column() {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.team_label))
                },
                value = scorecard.teamName,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredTeamName(it))
                })
            Spacer(modifier = modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.opponents_label))
                },
                value = scorecard.opponentsName,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredOpponentsName(it))
                })
            Spacer(modifier = modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    viewModel.onEvent(AddEditScorecardUiEvent.SaveScorecard)
                    onSaveOrCancel()
                }) {
                    Text(text = stringResource(R.string.save))
                }
                Row {
                    Button(onClick = onSaveOrCancel) {
                        Text(text = stringResource(R.string.cancel))
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddEditScorecardScreenPreview() {
    ScorerTheme { Surface { AddEditScorecardScreen(modifier = Modifier, onSaveOrCancel = {}) } }
}
