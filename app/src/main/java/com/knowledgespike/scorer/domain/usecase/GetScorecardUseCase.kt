package com.knowledgespike.scorer.domain.usecase

import com.knowledgespike.scorer.data.source.ScorecardDao
import com.knowledgespike.scorer.domain.entity.Scorecard

class GetScorecardUseCase(private val dao: ScorecardDao) {

    suspend operator fun invoke(scorecardId: Int) : Scorecard? {
        return dao.getScorecard(scorecardId)
    }
}
