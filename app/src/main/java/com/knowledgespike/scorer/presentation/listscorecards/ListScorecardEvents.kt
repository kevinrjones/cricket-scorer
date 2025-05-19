package com.knowledgespike.scorer.presentation.listscorecards

import com.knowledgespike.scorer.domain.model.ValidScorecard
import com.knowledgespike.scorer.domain.usecase.SortOrder

sealed class ListScorecardEvent {
    data class Order(val order: SortOrder) : ListScorecardEvent()
    data class Delete(val scorecard: ValidScorecard) : ListScorecardEvent()
}

