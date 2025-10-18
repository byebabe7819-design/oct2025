package com.verumomnis.brains

/**
 * Contradiction brain (B1) - deterministic toy implementation.
 */
class ContradictionBrain : IAnalysisBrain {
    override fun analyze(inputs: Map<String, Any>): AnalysisResult {
        val messages = (inputs["messages"] as? List<*>)?.size ?: 0
        val confidence = when {
            messages == 0 -> 1.0
            messages < 3 -> 0.6
            else -> 0.4
        }
        return AnalysisResult(
            conversationId = (inputs["conversationId"] as? String) ?: "unknown",
            confidence = confidence,
            flags = mapOf("messages" to messages, "brain" to "ContradictionBrain"),
            isPositive = confidence < 0.5
        )
    }
}
