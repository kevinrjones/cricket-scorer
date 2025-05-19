package com.knowledgespike.scorer.domain.usecase

data class ScorerUseCases(
    val getScorecards : GetScorecardsUseCase,
    val getScorecard : GetScorecardUseCase,
    val deleteScorecard : DeleteScorecardUseCase,
    val upsertScorecard : UpsertScorecardUseCase
)
