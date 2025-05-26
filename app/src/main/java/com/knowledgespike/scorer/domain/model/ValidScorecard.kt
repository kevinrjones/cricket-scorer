package com.knowledgespike.scorer.domain.model

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.raise.ExperimentalRaiseAccumulateApi
import arrow.core.raise.accumulate
import arrow.core.raise.either
import com.knowledgespike.scorer.R
import com.knowledgespike.scorer.domain.entity.Scorecard
import com.knowledgespike.scorer.type.error.EmptyStringError
import com.knowledgespike.scorer.type.error.ScorecardError

@ConsistentCopyVisibility
data class ValidScorecard private constructor(
    val id: Int?,
    val teamName: String,
    val opponentsName: String,
    val venue: String,
    val title: String,
    val matchDate: String,
    val battingSide: String,
    val umpire1Name: String?,
    val umpire2Name: String?,
    val thirdUmpireName: String?,
    val refereeName: String?,
    val scorer1Name: String,
    val scorer2Name: String?,
    val typeOfMatch: String,
    val duration: String,
    val startTime: String,
    val teamWinningToss: String,
    val weather: String?,
    val pitchCondition: String?,
) {
    companion object {
        @OptIn(ExperimentalRaiseAccumulateApi::class)
        operator fun invoke(
            id: Int?,
            teamName: String,
            opponentsName: String,
            venue: String,
            title: String,
            matchDate: String,
            battingSide: String,
            umpire1Name: String? = null,
            umpire2Name: String? = null,
            thirdUmpireName: String? = null,
            refereeName: String? = null,
            scorer1Name: String,
            scorer2Name: String? = null,
            typeOfMatch: String,
            duration: String,
            startTime: String,
            teamWinningToss: String,
            weather: String? = null,
            pitchCondition: String? = null,
        ): Either<NonEmptyList<ScorecardError>, ValidScorecard> = either {

            accumulate {
                ensureOrAccumulate(teamName.isNotEmpty()) { EmptyStringError(R.string.missing_team_name) }
                ensureOrAccumulate(opponentsName.isNotEmpty()) { EmptyStringError(R.string.missing_opponents_name) }
                ensureOrAccumulate(venue.isNotEmpty()) { EmptyStringError(R.string.missing_venue) }
                ensureOrAccumulate(title.isNotEmpty()) { EmptyStringError(R.string.missing_match_title) }
                ensureOrAccumulate(matchDate.isNotEmpty()) { EmptyStringError(R.string.missing_date) }
                ensureOrAccumulate(battingSide.isNotEmpty() && (battingSide == teamName || battingSide == opponentsName)) {
                    EmptyStringError(
                        R.string.invalid_batting_side_name
                    )
                }
                ensureOrAccumulate(scorer1Name.isNotEmpty()) { EmptyStringError(R.string.missing_scorer) }
                ensureOrAccumulate(typeOfMatch.isNotEmpty()) { EmptyStringError(R.string.missing_type_of_match) }
                ensureOrAccumulate(duration.isNotEmpty()) { EmptyStringError(R.string.missing_duration) }
                ensureOrAccumulate(duration.isNotEmpty()) { EmptyStringError(R.string.missing_duration) }
                ensureOrAccumulate(startTime.isNotEmpty()) { EmptyStringError(R.string.missing_start_time) }
                ensureOrAccumulate(teamWinningToss.isNotEmpty() && (teamWinningToss == teamName || teamWinningToss == opponentsName)) {
                    EmptyStringError(
                        R.string.invalid_team_winning_toss_name
                    )
                }
                ValidScorecard(
                    id,
                    teamName,
                    opponentsName,
                    venue,
                    title,
                    matchDate,
                    battingSide,
                    umpire1Name,
                    umpire2Name,
                    thirdUmpireName,
                    refereeName,
                    scorer1Name,
                    scorer2Name,
                    typeOfMatch,
                    duration,
                    startTime,
                    teamWinningToss,
                    weather,
                    pitchCondition
                )
            }


        }
    }
}

fun ValidScorecard.toEntity(): Scorecard {
    return Scorecard(
        id = this.id,
        teamName = this.teamName,
        opponentsName = this.opponentsName,
        venue = this.venue,
        title = this.title,
        matchDate = this.matchDate,
        battingSide = this.battingSide,
        umpire1Name = this.umpire1Name,
        umpire2Name = this.umpire2Name,
        thirdUmpireName = this.thirdUmpireName,
        refereeName = this.refereeName,
        scorer1Name = this.scorer1Name,
        scorer2Name = this.scorer2Name,
        typeOfMatch = this.typeOfMatch,
        duration = this.duration,
        startTime = this.startTime,
        teamWinningToss = this.teamWinningToss,
        weather = this.weather,
        pitchCondition = this.pitchCondition,
    )
}


