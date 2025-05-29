package com.knowledgespike.scorer.presentation

import kotlinx.serialization.Serializable

sealed class ScorerDestination

@Serializable
object ScorecardsListDestination : ScorerDestination()

@Serializable
data class AddEditScorecardDestination(val scorecardId: Int?) : ScorerDestination()
@Serializable
data class ScoreMatchDestination(val scorecardId: Int?) : ScorerDestination()
