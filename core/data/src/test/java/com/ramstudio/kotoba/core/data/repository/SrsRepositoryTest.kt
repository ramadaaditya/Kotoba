package com.ramstudio.kotoba.core.data.repository

import com.ramstudio.kotoba.core.database.dao.SrsDao
import com.ramstudio.kotoba.core.database.entity.SrsItem
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SrsRepositoryTest {

    private val srsDao: SrsDao = mock()
    private val srsRepository = SrsRepositoryImpl(srsDao)

    @Test
    fun `processReview should update item in database`() = runTest {
        val characterId = "a"
        val quality = 5
        
        whenever(srsDao.getSrsItem(characterId)).doReturn(null)

        srsRepository.processReview(characterId, quality)

        verify(srsDao).updateSrsItem(any())
    }

    @Test
    fun `processReview for existing item should apply SM2 logic`() = runTest {
        val characterId = "a"
        val quality = 5
        val existingItem = SrsItem(
            characterId = characterId,
            repetitionCount = 1,
            intervalDays = 1,
            easeFactor = 2.5f,
            nextReviewTimestamp = 0
        )
        
        whenever(srsDao.getSrsItem(characterId)).doReturn(existingItem)

        srsRepository.processReview(characterId, quality)

        verify(srsDao).updateSrsItem(org.mockito.kotlin.check {
            assertEquals(2, it.repetitionCount)
            assertEquals(6, it.intervalDays)
        })
    }
}
