package com.knowledgespike.scorer.presentation.model

import arrow.core.Either
import arrow.core.NonEmptyList
import com.knowledgespike.scorer.domain.entity.Scorecard
import com.knowledgespike.scorer.domain.model.ValidScorecard
import com.knowledgespike.scorer.type.error.ScorecardError

data class VmScorecard(
    val id: Int? = null,
    val teamName: String = "",
    val opponentsName: String = "",
    val venue: String = "",
    val title: String = "",
    val matchDate: String = "",
    val battingSide: String = "",
    val umpire1Name: String? = null,
    val umpire2Name: String? = null,
    val thirdUmpireName: String? = null,
    val refereeName: String? = null,
    val scorer1Name: String = "",
    val scorer2Name: String? = null,
    val typeOfMatch: String = "",
    val duration: String = "",
    val startTime: String = "",
    val teamWinningToss: String = "",
    val weather: String? = null,
    val pitchCondition: String? = "",
)  {
    companion object {
        fun fromEntity(scorecard: Scorecard): VmScorecard {

            return VmScorecard(
                id = scorecard.id,
                teamName = scorecard.teamName,
                opponentsName = scorecard.opponentsName,
                venue = scorecard.venue,
                title = scorecard.title,
                matchDate = scorecard.matchDate,
                battingSide = scorecard.battingSide,
                umpire1Name = scorecard.umpire1Name,
                umpire2Name = scorecard.umpire2Name,
                thirdUmpireName = scorecard.thirdUmpireName,
                refereeName = scorecard.refereeName,
                scorer1Name = scorecard.scorer1Name,
                scorer2Name = scorecard.scorer2Name,
                typeOfMatch = scorecard.typeOfMatch,
                duration = scorecard.duration,
                startTime = scorecard.startTime,
                teamWinningToss = scorecard.teamWinningToss,
                weather = scorecard.weather,
                pitchCondition = scorecard.pitchCondition,
            )
        }
    }
}

fun VmScorecard.toValidScorecard(): Either<NonEmptyList<ScorecardError>, ValidScorecard> =
    ValidScorecard(
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
