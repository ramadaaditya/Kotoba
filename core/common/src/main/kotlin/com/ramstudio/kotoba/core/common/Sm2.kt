package com.ramstudio.kotoba.core.common

import java.time.Instant
import kotlin.math.max

/**
 * Implementation of SM-2 algorithm for SuperMemo SRS.
 * Ref: https://www.supermemo.com/en/archives1990-2015/english/ol/sm2
 */
data class Sm2State(
    val repetitionCount: Int = 0,
    val intervalDays: Int = 0,
    val easeFactor: Float = 2.5f,
    val nextReview: Instant = Instant.now()
)

object Sm2 {
    /**
     * @param quality 0-5 (0: total blackout, 5: perfect response)
     */
    fun calculateNextState(currentState: Sm2State, quality: Int): Sm2State {
        val q = quality.coerceIn(0, 5)
        
        if (q < 3) {
            // Failure, reset repetition count but keep ease factor (or slightly reduce)
            return currentState.copy(
                repetitionCount = 0,
                intervalDays = 1,
                nextReview = Instant.now().plusSeconds(86400) // 1 day
            )
        }

        val newRepetitionCount = currentState.repetitionCount + 1
        val newIntervalDays = when (newRepetitionCount) {
            1 -> 1
            2 -> 6
            else -> (currentState.intervalDays * currentState.easeFactor).toInt()
        }

        val newEaseFactor = max(
            1.3f,
            currentState.easeFactor + (0.1f - (5 - q) * (0.08f + (5 - q) * 0.02f))
        )

        return Sm2State(
            repetitionCount = newRepetitionCount,
            intervalDays = newIntervalDays,
            easeFactor = newEaseFactor,
            nextReview = Instant.now().plusSeconds(newIntervalDays.toLong() * 86400)
        )
    }
}
