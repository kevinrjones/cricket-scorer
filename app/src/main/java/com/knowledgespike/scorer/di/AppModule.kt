package com.knowledgespike.scorer.di

import android.app.Application
import androidx.room.Room
import com.knowledgespike.scorer.data.source.ScorecardDatabase
import com.knowledgespike.scorer.domain.usecase.DeleteScorecardUseCase
import com.knowledgespike.scorer.domain.usecase.GetScorecardUseCase
import com.knowledgespike.scorer.domain.usecase.GetScorecardsUseCase
import com.knowledgespike.scorer.domain.usecase.ScorerUseCases
import com.knowledgespike.scorer.domain.usecase.UpsertScorecardUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Application) : ScorecardDatabase {
        return Room.databaseBuilder(
            context,
            ScorecardDatabase::class.java,
            ScorecardDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideScorerUseCases(db: ScorecardDatabase) : ScorerUseCases {
        return ScorerUseCases(
            getScorecards = GetScorecardsUseCase(db.scorecardDao),
            getScorecard = GetScorecardUseCase(db.scorecardDao),
            deleteScorecard = DeleteScorecardUseCase(db.scorecardDao),
            upsertScorecard = UpsertScorecardUseCase(db.scorecardDao)
        )
    }

}