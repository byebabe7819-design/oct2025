package com.verumomnis.brains

data class AnalysisResult(
    val conversationId: String,
    val confidence: Double,
    val flags: Map<String, Any> = emptyMap(),
    val isPositive: Boolean = false
)
