package com.verumomnis.brains

/**
 * Brain dispatcher orchestrates registered brains in deterministic order.
 */
object BrainDispatcher {
    val brains: List<IAnalysisBrain> = listOf(
        ContradictionBrain()
        // register additional brains here in a fixed order
    )

    fun dispatch(inputs: Map<String, Any>): List<AnalysisResult> {
        return brains.map { it.analyze(inputs) }
    }
}
