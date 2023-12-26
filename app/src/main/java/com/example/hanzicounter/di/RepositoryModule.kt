package com.example.hanzicounter.di

import com.example.hanzicounter.data.TextRepositoryImpl
import com.example.hanzicounter.domain.TextRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideTextRepository(repository: TextRepositoryImpl): TextRepository
}