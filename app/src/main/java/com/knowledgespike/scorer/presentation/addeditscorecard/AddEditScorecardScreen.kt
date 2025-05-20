package com.knowledgespike.scorer.presentation.addeditscorecard

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
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
    val scrollState = rememberScrollState()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Create a New Scorecard")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
                .padding(start = 16.dp, end = 16.dp, top = innerPadding.calculateTopPadding()),

            ) {

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.team_label))
                },
                value = scorecard.teamName,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredTeamName(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.teamName,
                    viewModel.fieldChanges.teamNameChanged
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.opponents_label))
                },
                value = scorecard.opponentsName,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredOpponentsName(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.opponentsName,
                    viewModel.fieldChanges.opponentsNameChanged
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.venue_label))
                },
                value = scorecard.venue,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredVenue(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.venue,
                    viewModel.fieldChanges.venueChanged
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.title_label))
                },
                value = scorecard.title,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredTitle(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.title,
                    viewModel.fieldChanges.titleChanged
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.date_label))
                },
                value = scorecard.matchDate,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredDate(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.matchDate,
                    viewModel.fieldChanges.dateChanged
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.batting_side_label))
                },
                value = scorecard.battingSide,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredBattingSide(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.battingSide,
                    viewModel.fieldChanges.battingSideChanged
                )|| !(viewModel.scorecard.value.battingSide == viewModel.scorecard.value.teamName
                        || viewModel.scorecard.value.battingSide == viewModel.scorecard.value.opponentsName)
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.umpire1_label))
                },
                value = scorecard.umpire1Name ?: "",
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredUmpire1Name(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.umpire2_label))
                },
                value = scorecard.umpire2Name ?: "",
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredUmpire2Name(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.third_umpire_label))
                },
                value = scorecard.thirdUmpireName ?: "",
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredThirdUmpireName(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.referee_label))
                },
                value = scorecard.refereeName ?: "",
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredReferee(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.scorer1_label))
                },
                value = scorecard.scorer1Name,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredScorer1Name(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.scorer1Name,
                    viewModel.fieldChanges.scorer1NameChanged
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.scorer2_label))
                },
                value = scorecard.scorer2Name ?: "",
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredScorer2Name(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.type_of_match_label))
                },
                value = scorecard.typeOfMatch,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredTypeOfMatch(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.typeOfMatch,
                    viewModel.fieldChanges.typeOfMatchChanged
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.duration_label))
                },
                value = scorecard.duration,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredDuration(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.duration,
                    viewModel.fieldChanges.durationChanged
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.start_time_label))
                },
                value = scorecard.startTime,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredStartTime(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.startTime,
                    viewModel.fieldChanges.startTimeChanged
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.team_winning_toss_label))
                },
                value = scorecard.teamWinningToss,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredTeamWinningToss(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.teamWinningToss,
                    viewModel.fieldChanges.teamWinningTossChanged
                ) || !(viewModel.scorecard.value.teamWinningToss == viewModel.scorecard.value.teamName
                        || viewModel.scorecard.value.teamWinningToss == viewModel.scorecard.value.opponentsName)
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.weather_label))
                },
                value = scorecard.weather ?: "",
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredWeather(it))
                },
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.pitch_conditions_label))
                },
                value = scorecard.pitchCondition ?: "",
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredPitchCondition(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))



            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    viewModel.onEvent(AddEditScorecardUiEvent.SaveScorecard)
                    onSaveOrCancel()
                }, enabled = viewModel.isValid) {
                    Text(text = stringResource(R.string.save))
                }
                Row {
                    Button(onClick = onSaveOrCancel) {
                        Text(text = stringResource(R.string.cancel))
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddEditScorecardScreenPreview() {
    ScorerTheme { Surface { AddEditScorecardScreen(modifier = Modifier, onSaveOrCancel = {}) } }
}
