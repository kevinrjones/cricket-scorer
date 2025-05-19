package com.knowledgespike.scorer.presentation.model

import arrow.core.Either
import arrow.core.NonEmptyList
import com.knowledgespike.scorer.domain.entity.Scorecard
import com.knowledgespike.scorer.domain.model.ValidScorecard
import com.knowledgespike.scorer.domain.model.ValidScorecard.Companion.invoke
import com.knowledgespike.scorer.type.error.ScorecardError

data class VmScorecard(
    val teamName: String,
    val opponentsName: String,
    val matchStartDate: Long,
) {
    companion object {
        fun fromEntity(scorecard: Scorecard): VmScorecard {

            return VmScorecard(
                teamName = scorecard.teamName,
                opponentsName = scorecard.opponentsName,
                matchStartDate = scorecard.matchStartDate
            )
        }
    }
}

fun VmScorecard.toValidScorecard(): Either<NonEmptyList<ScorecardError>, ValidScorecard> = ValidScorecard(
    teamName = this.teamName,
    opponentsName = this.opponentsName,
    matchStartDate = this.matchStartDate
)
