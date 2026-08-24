package com.ramstudio.kotoba.core.common

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class Sm2Test {

    @Test
    fun `first repetition should have 1 day interval`() {
        val initialState = Sm2State()
        val nextState = Sm2.calculateNextState(initialState, 5)
        
        assertEquals(1, nextState.repetitionCount)
        assertEquals(1, nextState.intervalDays)
    }

    @Test
    fun `second repetition should have 6 days interval`() {
        val state1 = Sm2.calculateNextState(Sm2State(), 5)
        val state2 = Sm2.calculateNextState(state1, 5)
        
        assertEquals(2, state2.repetitionCount)
        assertEquals(6, state2.intervalDays)
    }

    @Test
    fun `failure quality should reset repetition count`() {
        val state1 = Sm2.calculateNextState(Sm2State(), 5)
        val failureState = Sm2.calculateNextState(state1, 2)
        
        assertEquals(0, failureState.repetitionCount)
        assertEquals(1, failureState.intervalDays)
    }

    @Test
    fun `ease factor should increase for perfect quality`() {
        val initialState = Sm2State(easeFactor = 2.5f)
        val nextState = Sm2.calculateNextState(initialState, 5)
        
        assertTrue("Ease factor should increase: ${nextState.easeFactor}", nextState.easeFactor > 2.5f)
    }
}
