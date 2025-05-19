package com.knowledgespike.scorer.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Scorecards")
data class Scorecard(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val teamName: String,
    val opponentsName: String,
    val matchStartDate: Long,
)