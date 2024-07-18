package com.abztest.di

import android.content.Context
import androidx.room.Room
import com.abztest.data.database.AppDatabase
import com.abztest.data.database.RequestDao
import com.abztest.data.repository.RequestRepositoryImpl
import com.abztest.domain.RequestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Dagger Hilt module to provide data-related dependencies
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    // Provides the AppDatabase instance, with a singleton scope
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "requests_db"
        ).fallbackToDestructiveMigration().build()
    }

    // Provides the RequestDao instance
    @Provides
    fun provideRequestDao(appDatabase: AppDatabase): RequestDao {
        return appDatabase.requestDao()
    }

    // Provides the RequestRepository instance
    @Provides
    fun provideRequestRepository(requestDao: RequestDao): RequestRepository {
        return RequestRepositoryImpl(requestDao)
    }
}
