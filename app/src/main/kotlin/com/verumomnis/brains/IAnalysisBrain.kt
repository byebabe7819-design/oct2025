package com.verumomnis.brains

/**
 * Core analysis brain interface. Implementations must be pure and deterministic.
 */
interface IAnalysisBrain {
    fun analyze(inputs: Map<String, Any>): AnalysisResult
}
