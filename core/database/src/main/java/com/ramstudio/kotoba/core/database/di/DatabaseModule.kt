package com.ramstudio.kotoba.core.database.di

import android.content.Context
import androidx.room.Room
import com.ramstudio.kotoba.core.database.KotobaDatabase
import com.ramstudio.kotoba.core.database.dao.KanaDao
import com.ramstudio.kotoba.core.database.dao.SrsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): KotobaDatabase {
        return Room.databaseBuilder(
            context,
            KotobaDatabase::class.java,
            "kotoba_db"
        ).build()
    }

    @Provides
    fun provideKanaDao(database: KotobaDatabase): KanaDao = database.kanaDao()

    @Provides
    fun provideSrsDao(database: KotobaDatabase): SrsDao = database.srsDao()
}
