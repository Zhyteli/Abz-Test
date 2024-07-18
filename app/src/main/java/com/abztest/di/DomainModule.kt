package com.abztest.di

import com.abztest.domain.RequestRepository
import com.abztest.domain.usecase.DeleteRequestUseCase
import com.abztest.domain.usecase.GetFilteredRequestsUseCase
import com.abztest.domain.usecase.InsertRequestUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Dagger Hilt module to provide domain-level dependencies
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    // Provides the DeleteRequestUseCase instance
    @Provides
    fun provideDeleteRequestUseCase(repository: RequestRepository): DeleteRequestUseCase {
        return DeleteRequestUseCase(repository)
    }

    // Provides the GetFilteredRequestsUseCase instance
    @Provides
    fun provideGetFilteredRequestsUseCase(repository: RequestRepository): GetFilteredRequestsUseCase {
        return GetFilteredRequestsUseCase(repository)
    }

    // Provides the InsertRequestUseCase instance
    @Provides
    fun provideInsertRequestUseCase(repository: RequestRepository): InsertRequestUseCase {
        return InsertRequestUseCase(repository)
    }
}