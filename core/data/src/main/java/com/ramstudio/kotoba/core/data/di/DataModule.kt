package com.ramstudio.kotoba.core.data.di

import com.ramstudio.kotoba.core.data.repository.KanaRepository
import com.ramstudio.kotoba.core.data.repository.KanaRepositoryImpl
import com.ramstudio.kotoba.core.data.repository.SrsRepository
import com.ramstudio.kotoba.core.data.repository.SrsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindKanaRepository(
        kanaRepositoryImpl: KanaRepositoryImpl
    ): KanaRepository

    @Binds
    fun bindSrsRepository(
        srsRepositoryImpl: SrsRepositoryImpl
    ): SrsRepository
}
