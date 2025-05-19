package com.knowledgespike.scorer.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.knowledgespike.scorer.domain.entity.Scorecard

@Database(entities = [Scorecard::class], version = 1)
abstract class ScorecardDatabase : RoomDatabase() {

    abstract val scorecardDao: ScorecardDao

    companion object {
        const val DATABASE_NAME = "scorecards_db"
    }
}