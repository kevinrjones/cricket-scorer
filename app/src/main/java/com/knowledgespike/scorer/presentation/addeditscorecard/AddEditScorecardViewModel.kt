package com.knowledgespike.scorer.presentation.addeditscorecard

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


@HiltViewModel
class AddEditScorecardViewModel @Inject constructor(
    private val useCases: ScorerUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _scorecard = mutableStateOf(VmScorecard("", "", 0))
    val scorecard: State<VmScorecard> = _scorecard

    private val _eventFlow = MutableSharedFlow<AddEditScorecardEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        val scorecardId = savedStateHandle.get<Int>("scorecardId") ?: -1
        findScorecard(scorecardId)
    }

    private fun findScorecard(scorecardId: Int) {
        viewModelScope.launch {
            val scorecardEntity = useCases.getScorecard(scorecardId)
            _scorecard.value =
                scorecardEntity?.let { VmScorecard.fromEntity(it) } ?: VmScorecard("", "", 0)
        }
    }

    fun onEvent(event: AddEditScorecardUiEvent) {
        when (event) {

            AddEditScorecardUiEvent.SaveScorecard -> {
                viewModelScope.launch {

                    if (scorecard.value.teamName.isEmpty()) {
                        _eventFlow.emit(AddEditScorecardEvent.ErrorSavingScorecard)
                    } else {
                        // todo: save the scorecard
                        // log the issues here - need to add analytics
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
            }

            is AddEditScorecardUiEvent.EnteredTeamName -> _scorecard.value =
                _scorecard.value.copy(teamName = event.teamName)

            is AddEditScorecardUiEvent.EnteredOpponentsName -> _scorecard.value =
                _scorecard.value.copy(opponentsName = event.opponentsName)
        }
    }
}

sealed interface AddEditScorecardEvent {
    data object SavedScorecard : AddEditScorecardEvent
    data object ErrorSavingScorecard : AddEditScorecardEvent
}

sealed interface AddEditScorecardUiEvent {
    data class EnteredTeamName(val teamName: String) : AddEditScorecardUiEvent
    data class EnteredOpponentsName(val opponentsName: String) : AddEditScorecardUiEvent
    data object SaveScorecard : AddEditScorecardUiEvent
}
