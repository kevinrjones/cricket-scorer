package com.knowledgespike.scorer.domain.usecase

import com.knowledgespike.scorer.data.source.ScorecardDao
import com.knowledgespike.scorer.domain.model.ValidScorecard
import com.knowledgespike.scorer.domain.model.toEntity

class DeleteScorecardUseCase (private val dao: ScorecardDao) {

    operator suspend fun invoke(validScorecard: ValidScorecard) {
        val entity = validScorecard.toEntity()
        dao.deleteScorecard(entity)
    }
}
