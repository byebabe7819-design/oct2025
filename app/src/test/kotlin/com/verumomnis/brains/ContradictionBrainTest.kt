package com.verumomnis.brains

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ContradictionBrainTest {
    @Test
    fun `contradiction brain returns expected confidence for three messages`() {
        val brain = ContradictionBrain()
        val inputs = mapOf("conversationId" to "conv1", "messages" to listOf("a","b","c"))

        val result = brain.analyze(inputs)

        assertEquals("conv1", result.conversationId)
        assertEquals(0.4, result.confidence, 1e-9)
        assertEquals("ContradictionBrain", result.flags["brain"])
    }
}
