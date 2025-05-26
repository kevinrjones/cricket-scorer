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
import androidx.compose.ui.unit.sp
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

            AddEditScorecardScreenField(
                value = scorecard.teamName,
                labelId = R.string.team_label,
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.teamName,
                    viewModel.fieldChanges.teamNameChanged
                ),
                errorMessageId = R.string.missing_team_name,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredTeamName(it))
                })
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                value = scorecard.opponentsName,
                labelId = R.string.opponents_label,
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.opponentsName,
                    viewModel.fieldChanges.opponentsNameChanged
                ),
                errorMessageId = R.string.missing_opponents_name,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredOpponentsName(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                value = scorecard.venue,
                labelId = R.string.venue_label,
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.venue,
                    viewModel.fieldChanges.venueChanged
                ),
                errorMessageId = R.string.missing_venue,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredVenue(it))
                },
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                value = scorecard.title,
                labelId = R.string.title_label,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredTitle(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.title,
                    viewModel.fieldChanges.titleChanged
                ),
                errorMessageId = R.string.missing_match_title
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                value = scorecard.matchDate,
                labelId = R.string.date_label,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredDate(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.matchDate,
                    viewModel.fieldChanges.dateChanged
                ),
                errorMessageId = R.string.missing_date
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                labelId = R.string.batting_side_label,
                value = scorecard.battingSide,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredBattingSide(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.battingSide,
                    viewModel.fieldChanges.battingSideChanged
                ) || !(viewModel.scorecard.value.battingSide == viewModel.scorecard.value.teamName
                        || viewModel.scorecard.value.battingSide == viewModel.scorecard.value.opponentsName),
                errorMessageId = R.string.invalid_batting_side_name
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                value = scorecard.umpire1Name ?: "",
                labelId = R.string.umpire1_label,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredUmpire1Name(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                value = scorecard.umpire2Name ?: "",
                labelId = R.string.umpire2_label,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredUmpire2Name(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                value = scorecard.thirdUmpireName ?: "",
                labelId = R.string.third_umpire_label,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredThirdUmpireName(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                value = scorecard.refereeName ?: "",
                labelId = R.string.referee_label,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredReferee(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                value = scorecard.scorer1Name,
                labelId = R.string.scorer1_label,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredScorer1Name(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.scorer1Name,
                    viewModel.fieldChanges.scorer1NameChanged
                ),
                errorMessageId = R.string.missing_scorer
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                value = scorecard.scorer2Name ?: "",
                labelId = R.string.scorer2_label,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredScorer2Name(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                labelId = R.string.type_of_match_label,
                value = scorecard.typeOfMatch,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredTypeOfMatch(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.typeOfMatch,
                    viewModel.fieldChanges.typeOfMatchChanged
                ),
                errorMessageId = R.string.missing_type_of_match
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                labelId = R.string.duration_label,
                value = scorecard.duration,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredDuration(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.duration,
                    viewModel.fieldChanges.durationChanged
                ),
                errorMessageId = R.string.missing_duration
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                labelId = R.string.start_time_label,
                value = scorecard.startTime,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredStartTime(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.startTime,
                    viewModel.fieldChanges.startTimeChanged
                ),
                errorMessageId = R.string.missing_start_time
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                labelId = R.string.team_winning_toss_label,
                value = scorecard.teamWinningToss,
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredTeamWinningToss(it))
                },
                isError = viewModel.isEmpty(
                    viewModel.scorecard.value.teamWinningToss,
                    viewModel.fieldChanges.teamWinningTossChanged
                ) || !(viewModel.scorecard.value.teamWinningToss == viewModel.scorecard.value.teamName
                        || viewModel.scorecard.value.teamWinningToss == viewModel.scorecard.value.opponentsName),
                errorMessageId = R.string.invalid_team_winning_toss_name
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                labelId = R.string.weather_label,
                value = scorecard.weather ?: "",
                onValueChange = {
                    viewModel.onEvent(AddEditScorecardUiEvent.EnteredWeather(it))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            AddEditScorecardScreenField(
                labelId = R.string.pitch_conditions_label,
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

@Composable
fun AddEditScorecardScreenField(
    value: String,
    labelId: Int,
    isError: Boolean = false,
    errorMessageId: Int? = null,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(stringResource(labelId))
        },
        value = value,
        onValueChange = {
            onValueChange(it)

        },
        isError = isError,
        singleLine = true
    )
    if (isError && errorMessageId != null) {
        Text(
            text = stringResource(errorMessageId),
            color = MaterialTheme.colorScheme.error,
            fontSize = 16.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AddEditScorecardScreenPreview() {
    ScorerTheme { Surface { AddEditScorecardScreen(modifier = Modifier, onSaveOrCancel = {}) } }
}
