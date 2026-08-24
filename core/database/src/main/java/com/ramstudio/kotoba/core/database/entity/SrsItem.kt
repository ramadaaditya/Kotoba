package com.ramstudio.kotoba.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "srs_items")
data class SrsItem(
    @PrimaryKey val characterId: String,
    val repetitionCount: Int,
    val intervalDays: Int,
    val easeFactor: Float,
    val nextReviewTimestamp: Long
)
