package com.ramstudio.kotoba.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramstudio.kotoba.core.database.entity.SrsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SrsDao {
    @Query("SELECT * FROM srs_items")
    fun getAllSrsItems(): Flow<List<SrsItem>>

    @Query("SELECT * FROM srs_items WHERE nextReviewTimestamp <= :currentTimestamp")
    fun getDueItems(currentTimestamp: Long): Flow<List<SrsItem>>

    @Query("SELECT * FROM srs_items WHERE characterId = :characterId")
    suspend fun getSrsItem(characterId: String): SrsItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSrsItem(item: SrsItem)
}
