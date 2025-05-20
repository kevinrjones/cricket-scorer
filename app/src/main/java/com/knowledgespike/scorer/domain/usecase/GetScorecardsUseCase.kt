package com.knowledgespike.scorer.domain.usecase

import com.knowledgespike.scorer.data.source.ScorecardDao
import com.knowledgespike.scorer.domain.entity.Scorecard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetScorecardsUseCase(private val dao: ScorecardDao) {

    operator fun invoke(orderBy: SortOrder = SortByTeam): Flow<List<Scorecard>> {
        return dao.getScorecards()
            .map { scorecards ->
                when (orderBy) {
                    SortByTeam -> scorecards.sortedBy { it.teamName }
                    SortByOpponents -> scorecards.sortedBy { it.opponentsName }
                    SortByDate -> scorecards.sortedBy { it.matchDate }
                }
            }
    }

}
