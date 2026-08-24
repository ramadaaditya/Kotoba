package com.ramstudio.kotoba.core.data.repository

import com.ramstudio.kotoba.core.common.Sm2
import com.ramstudio.kotoba.core.common.Sm2State
import com.ramstudio.kotoba.core.database.dao.SrsDao
import com.ramstudio.kotoba.core.database.entity.SrsItem
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

interface SrsRepository {
    fun getDueItems(currentTimestamp: Long): Flow<List<SrsItem>>
    suspend fun processReview(characterId: String, quality: Int)
}

@Singleton
class SrsRepositoryImpl @Inject constructor(
    private val srsDao: SrsDao
) : SrsRepository {
    override fun getDueItems(currentTimestamp: Long): Flow<List<SrsItem>> = 
        srsDao.getDueItems(currentTimestamp)

    override suspend fun processReview(characterId: String, quality: Int) {
        val currentItem = srsDao.getSrsItem(characterId) ?: SrsItem(
            characterId = characterId,
            repetitionCount = 0,
            intervalDays = 0,
            easeFactor = 2.5f,
            nextReviewTimestamp = Instant.now().toEpochMilli()
        )

        val currentState = Sm2State(
            repetitionCount = currentItem.repetitionCount,
            intervalDays = currentItem.intervalDays,
            easeFactor = currentItem.easeFactor,
            nextReview = Instant.ofEpochMilli(currentItem.nextReviewTimestamp)
        )

        val nextState = Sm2.calculateNextState(currentState, quality)

        val updatedItem = currentItem.copy(
            repetitionCount = nextState.repetitionCount,
            intervalDays = nextState.intervalDays,
            easeFactor = nextState.easeFactor,
            nextReviewTimestamp = nextState.nextReview.toEpochMilli()
        )

        srsDao.updateSrsItem(updatedItem)
    }
}
