package com.knowledgespike.scorer.domain.model

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.zipOrAccumulate
import arrow.core.right
import com.knowledgespike.scorer.R
import com.knowledgespike.scorer.domain.entity.Scorecard
import com.knowledgespike.scorer.type.error.EmptyStringError
import com.knowledgespike.scorer.type.error.ScorecardError

@ConsistentCopyVisibility
data class ValidScorecard private constructor(
    val teamName: String,
    val opponentsName: String,
    val matchStartDate: Long,
) {
    companion object {
        operator fun invoke(
            teamName: String,
            opponentsName: String,
            matchStartDate: Long,
        ): Either<NonEmptyList<ScorecardError>, ValidScorecard> = either {
            zipOrAccumulate(
                { ensure(teamName.isNotEmpty()) { EmptyStringError(R.string.missing_team_name) } },
                { ensure(opponentsName.isNotEmpty()) { EmptyStringError(R.string.missing_team_name) } },
            ) { _, _ ->
                ValidScorecard(teamName, opponentsName, matchStartDate)
            }
        }
    }
}

fun ValidScorecard.toEntity(): Scorecard {
    return Scorecard(
        teamName = this.teamName,
        opponentsName = this.opponentsName,
        matchStartDate = this.matchStartDate
    )
}


