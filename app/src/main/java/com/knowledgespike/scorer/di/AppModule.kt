package com.knowledgespike.scorer.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideBooksDatabase(context: Application) : BooksDatabase {
//        return Room.databaseBuilder(
//            context,
//            BooksDatabase::class.java,
//            BooksDatabase.DATABASE_NAME
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideBooksUseCases(db: BooksDatabase) : BooksUseCases {
//        return BooksUseCases(
//            getBooks = GetBooksUseCase(db.dao),
//            getBook = GetBookUseCase(db.dao),
//            deleteBook = DeleteBookUseCase(db.dao),
//            upsertBook = UpsertBookUseCase(db.dao)
//        )
//    }

}