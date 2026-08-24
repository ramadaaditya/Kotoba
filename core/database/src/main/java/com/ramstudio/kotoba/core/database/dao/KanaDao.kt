package com.ramstudio.kotoba.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramstudio.kotoba.core.database.entity.KanaCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface KanaDao {
    @Query("SELECT * FROM kana_characters")
    fun getAllCharacters(): Flow<List<KanaCharacter>>

    @Query("SELECT * FROM kana_characters WHERE type = :type")
    fun getCharactersByType(type: String): Flow<List<KanaCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<KanaCharacter>)
}
