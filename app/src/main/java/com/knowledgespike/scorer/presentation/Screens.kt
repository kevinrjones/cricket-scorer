package com.knowledgespike.scorer.presentation

import kotlinx.serialization.Serializable


@Serializable
object ScorecardsListDestination

@Serializable
data class AddEditScorecardDestination(val scorecardId: Int)
