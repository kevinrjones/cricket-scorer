package com.knowledgespike.scorer.presentation.listscorecards

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knowledgespike.scorer.domain.model.ValidScorecard
import com.knowledgespike.scorer.domain.usecase.ScorerUseCases
import com.knowledgespike.scorer.domain.usecase.SortByDate
import com.knowledgespike.scorer.domain.usecase.SortOrder
import com.knowledgespike.scorer.presentation.model.VmScorecard
import com.knowledgespike.scorer.presentation.model.toValidScorecard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectScorecardViewModel @Inject constructor(
    private val useCases: ScorerUseCases
) : ViewModel() {
    private val _scorecards: MutableState<List<VmScorecard>> = mutableStateOf(emptyList())
    var scorecards: State<List<VmScorecard>> = _scorecards

    private var _sortOrder: MutableState<SortOrder> = mutableStateOf(SortByDate)
    var sortOrder: State<SortOrder> = _sortOrder

    var job: Job? = null

    init {
        loadScorecards(sortOrder.value)
    }

    private fun loadScorecards(sortOrder: SortOrder) {
        job?.cancel()

        job = useCases.getScorecards(sortOrder).onEach { scorecards ->
            _scorecards.value = scorecards.map {
                VmScorecard.fromEntity(it)
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ListScorecardEvent) {
        when (event) {
            is ListScorecardEvent.Delete -> {
                deleteBook(event.scorecard)
            }

            is ListScorecardEvent.Order -> {
                _sortOrder.value = event.order
                loadScorecards(event.order)
            }
        }
    }

    private fun deleteBook(scorecard: ValidScorecard) {
        viewModelScope.launch {
            useCases.deleteScorecard(scorecard)
        }
    }
}

