package com.ramstudio.kotoba.core.common

/**
 * Interface for providing explanations for learning content.
 * Section 10.1 of ARSITEKTUR.md mentions: "Content explanation handled via `ExplanationProvider` interface"
 */
interface ExplanationProvider {
    fun getExplanation(contentId: String): String
}
