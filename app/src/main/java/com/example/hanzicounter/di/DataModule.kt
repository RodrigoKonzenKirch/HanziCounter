package com.example.hanzicounter.di

import android.content.Context
import androidx.room.Room
import com.example.hanzicounter.data.AppDatabase
import com.example.hanzicounter.data.TextDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.hanzicounter.utilities.Constants.DATABASE_NAME

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext appContext: Context,
    ): AppDatabase{
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesTextDao(appDatabase: AppDatabase): TextDao {
        return appDatabase.TextDao()
    }

}