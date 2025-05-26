package com.knowledgespike.scorer.presentation.screens.addeditscorecard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knowledgespike.scorer.domain.usecase.ScorerUseCases
import com.knowledgespike.scorer.presentation.model.VmScorecard
import com.knowledgespike.scorer.presentation.model.toValidScorecard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FieldChanges(
    val teamNameChanged: Boolean = false,
    val opponentsNameChanged: Boolean = false,
    val dateChanged: Boolean = false,
    val venueChanged: Boolean = false,
    val titleChanged: Boolean = false,
    val matchDateChanged: Boolean = false,
    val battingSideChanged: Boolean = false,
    val scorer1NameChanged: Boolean = false,
    val durationChanged: Boolean = false,
    val typeOfMatchChanged: Boolean = false,
    val startTimeChanged: Boolean = false,
    val teamWinningTossChanged: Boolean = false,
)

@HiltViewModel
class AddEditScorecardViewModel @Inject constructor(
    private val useCases: ScorerUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _scorecard = mutableStateOf(
        VmScorecard()
    )
    val scorecard: State<VmScorecard> = _scorecard

    private val _eventFlow = MutableSharedFlow<AddEditScorecardEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var fieldChanges = FieldChanges()

    init {
        val scorecardId = savedStateHandle.get<Int>("scorecardId")
        findScorecard(scorecardId)
    }

    private fun findScorecard(scorecardId: Int?) {
        viewModelScope.launch {
            if (scorecardId == null) {
                _scorecard.value = VmScorecard()
            } else {
                val scorecardEntity = useCases.getScorecard(scorecardId)
                _scorecard.value =
                    scorecardEntity?.let { VmScorecard.fromEntity(it) } ?: VmScorecard()
            }
        }
    }

    val isValid: Boolean
        get() = scorecard.value.toValidScorecard().isRight()


    fun onEvent(event: AddEditScorecardUiEvent) {
        when (event) {

            AddEditScorecardUiEvent.SaveScorecard -> {
                viewModelScope.launch {

                    // todo: log the issues here - need to add analytics
                    val validationResult = scorecard.value.toValidScorecard()
                    validationResult.fold(
                        ifLeft = {
                            _eventFlow.emit(AddEditScorecardEvent.ErrorSavingScorecard)
                        },
                        ifRight = {
                            useCases.upsertScorecard(scorecard = it)
                            _eventFlow.emit(AddEditScorecardEvent.SavedScorecard)
                        }
                    )
                }
            }

            is AddEditScorecardUiEvent.EnteredTeamName -> {
                fieldChanges = fieldChanges.copy(teamNameChanged = true)
                _scorecard.value = _scorecard.value.copy(teamName = event.teamName)
            }

            is AddEditScorecardUiEvent.EnteredOpponentsName -> {
                fieldChanges = fieldChanges.copy(opponentsNameChanged = true)
                _scorecard.value = _scorecard.value.copy(opponentsName = event.opponentsName)
            }

            is AddEditScorecardUiEvent.EnteredDate -> {
                fieldChanges = fieldChanges.copy(dateChanged = true)
                _scorecard.value =
                    _scorecard.value.copy(matchDate = event.matchDate)
            }

            is AddEditScorecardUiEvent.EnteredBattingSide -> {
                fieldChanges = fieldChanges.copy(battingSideChanged = true)
                _scorecard.value = _scorecard.value.copy(battingSide = event.battingSide)
            }

            is AddEditScorecardUiEvent.EnteredPitchCondition -> _scorecard.value =
                _scorecard.value.copy(pitchCondition = event.pitchCondition)

            is AddEditScorecardUiEvent.EnteredReferee -> _scorecard.value =
                _scorecard.value.copy(refereeName = event.name)

            is AddEditScorecardUiEvent.EnteredScorer1Name -> {
                fieldChanges = fieldChanges.copy(scorer1NameChanged = true)
                _scorecard.value = _scorecard.value.copy(scorer1Name = event.name)
            }

            is AddEditScorecardUiEvent.EnteredScorer2Name -> _scorecard.value =
                _scorecard.value.copy(scorer2Name = event.name)

            is AddEditScorecardUiEvent.EnteredStartTime -> {
                fieldChanges = fieldChanges.copy(startTimeChanged = true)
                _scorecard.value = _scorecard.value.copy(startTime = event.startTime)
            }

            is AddEditScorecardUiEvent.EnteredTeamWinningToss -> {
                fieldChanges = fieldChanges.copy(teamWinningTossChanged = true)
                _scorecard.value = _scorecard.value.copy(teamWinningToss = event.teamWinningToss)
            }

            is AddEditScorecardUiEvent.EnteredThirdUmpireName -> _scorecard.value =
                _scorecard.value.copy(thirdUmpireName = event.name)

            is AddEditScorecardUiEvent.EnteredTitle -> {
                fieldChanges = fieldChanges.copy(titleChanged = true)
                _scorecard.value = _scorecard.value.copy(title = event.title)
            }

            is AddEditScorecardUiEvent.EnteredTypeOfMatch -> {
                fieldChanges = fieldChanges.copy(typeOfMatchChanged = true)
                _scorecard.value = _scorecard.value.copy(typeOfMatch = event.typeOfMatch)
            }

            is AddEditScorecardUiEvent.EnteredUmpire1Name -> _scorecard.value =
                _scorecard.value.copy(umpire1Name = event.name)

            is AddEditScorecardUiEvent.EnteredUmpire2Name -> _scorecard.value =
                _scorecard.value.copy(umpire2Name = event.name)

            is AddEditScorecardUiEvent.EnteredVenue -> {
                fieldChanges = fieldChanges.copy(venueChanged = true)
                _scorecard.value = _scorecard.value.copy(venue = event.venue)
            }

            is AddEditScorecardUiEvent.EnteredWeather -> _scorecard.value =
                _scorecard.value.copy(weather = event.weather)

            is AddEditScorecardUiEvent.EnteredDuration -> {
                fieldChanges = fieldChanges.copy(durationChanged = true)
                _scorecard.value = _scorecard.value.copy(duration = event.duration)
            }
        }
    }

    fun isEmpty(value: String, changed: Boolean): Boolean {
        return value.isBlank() && changed
    }
}

sealed interface AddEditScorecardEvent {
    data object SavedScorecard : AddEditScorecardEvent
    data object ErrorSavingScorecard : AddEditScorecardEvent
}

sealed interface AddEditScorecardUiEvent {
    data class EnteredTeamName(val teamName: String) : AddEditScorecardUiEvent
    data class EnteredOpponentsName(val opponentsName: String) : AddEditScorecardUiEvent
    data class EnteredVenue(val venue: String) : AddEditScorecardUiEvent
    data class EnteredTitle(val title: String) : AddEditScorecardUiEvent
    data class EnteredDate(val matchDate: String) : AddEditScorecardUiEvent
    data class EnteredBattingSide(val battingSide: String) : AddEditScorecardUiEvent
    data class EnteredUmpire1Name(val name: String) : AddEditScorecardUiEvent
    data class EnteredUmpire2Name(val name: String) : AddEditScorecardUiEvent
    data class EnteredThirdUmpireName(val name: String) : AddEditScorecardUiEvent
    data class EnteredReferee(val name: String) : AddEditScorecardUiEvent
    data class EnteredScorer1Name(val name: String) : AddEditScorecardUiEvent
    data class EnteredScorer2Name(val name: String) : AddEditScorecardUiEvent
    data class EnteredDuration(val duration: String) : AddEditScorecardUiEvent
    data class EnteredTypeOfMatch(val typeOfMatch: String) : AddEditScorecardUiEvent
    data class EnteredStartTime(val startTime: String) : AddEditScorecardUiEvent
    data class EnteredTeamWinningToss(val teamWinningToss: String) : AddEditScorecardUiEvent
    data class EnteredWeather(val weather: String) : AddEditScorecardUiEvent
    data class EnteredPitchCondition(val pitchCondition: String) : AddEditScorecardUiEvent

    data object SaveScorecard : AddEditScorecardUiEvent
}
