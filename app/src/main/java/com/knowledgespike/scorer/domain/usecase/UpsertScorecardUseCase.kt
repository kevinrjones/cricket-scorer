package com.knowledgespike.scorer.domain.usecase

import com.knowledgespike.scorer.data.source.ScorecardDao
import com.knowledgespike.scorer.domain.model.ValidScorecard
import com.knowledgespike.scorer.domain.model.toEntity

class UpsertScorecardUseCase(private val dao: ScorecardDao) {

    suspend operator fun invoke(scorecard: ValidScorecard) {
        dao.upsertScorecard(scorecard.toEntity())
    }
}
