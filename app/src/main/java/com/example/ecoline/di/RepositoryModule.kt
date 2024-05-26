package com.example.ecoline.di

import com.example.ecoline.data.remote.api.EcolineRecycleAPI
import com.example.ecoline.data.repository.RecyclePointRepositoryImpl
import com.example.ecoline.domain.repository.RecyclePointRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRecyclePointRepository(api: EcolineRecycleAPI): RecyclePointRepository {
        return RecyclePointRepositoryImpl(ecolineRecycleAPI = api)
    }

}