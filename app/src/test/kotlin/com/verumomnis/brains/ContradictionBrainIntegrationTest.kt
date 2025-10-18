package com.verumomnis.brains

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ContradictionBrainIntegrationTest {
    @Test
    fun `dispatcher runs contradiction brain and returns expected findings`() {
        val inputs = mapOf(
            "conversationId" to "conv_1",
            "messages" to listOf("a", "b", "c")
        )

        val findings = BrainDispatcher.dispatch(inputs)

        // single registered brain -> one result object
        assertEquals(1, findings.size)
        val f = findings[0]
        assertEquals("conv_1", f.conversationId)
        assertEquals(0.4, f.confidence)
    }
}
