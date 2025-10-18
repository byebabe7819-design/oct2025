package com.verumomnis.tests

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import com.verumomnis.brains.BrainDispatcher

class ContradictionBrainIntegrationTest {

    @Test
    fun testContradictionBrainProducesPositiveResult() {
        val inputs = mapOf(
            "conversationId" to "conv_integ",
            "messages" to listOf("a", "b", "c")
        )

        val results = com.verumomnis.brains.BrainDispatcher.dispatch(inputs)

        // assert that at least one brain produced a positive flag
        assertTrue(
            results.any { it.isPositive },
            "Expected at least one positive brain result, but got none."
        )
    }
}
