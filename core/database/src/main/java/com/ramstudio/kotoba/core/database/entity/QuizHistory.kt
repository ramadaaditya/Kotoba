package com.ramstudio.kotoba.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_history")
data class QuizHistory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val characterId: String,
    val isCorrect: Boolean,
    val timestamp: Long
)
