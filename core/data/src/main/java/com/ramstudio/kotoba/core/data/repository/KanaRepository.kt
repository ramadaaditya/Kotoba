package com.ramstudio.kotoba.core.data.repository

import com.ramstudio.kotoba.core.database.dao.KanaDao
import com.ramstudio.kotoba.core.database.entity.KanaCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface KanaRepository {
    fun getAllCharacters(): Flow<List<KanaCharacter>>
    fun getCharactersByType(type: String): Flow<List<KanaCharacter>>
    suspend fun seedInitialData(characters: List<KanaCharacter>)
}

@Singleton
class KanaRepositoryImpl @Inject constructor(
    private val kanaDao: KanaDao
) : KanaRepository {
    override fun getAllCharacters(): Flow<List<KanaCharacter>> = kanaDao.getAllCharacters()

    override fun getCharactersByType(type: String): Flow<List<KanaCharacter>> = kanaDao.getCharactersByType(type)

    override suspend fun seedInitialData(characters: List<KanaCharacter>) {
        kanaDao.insertCharacters(characters)
    }
}
