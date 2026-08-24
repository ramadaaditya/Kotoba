plugins {
    kotlin("jvm")
}

// core:common should NOT have android dependencies as per AGENTS.md
// It must be testable on pure JVM

dependencies {
    // Basic Kotlin dependencies if needed
    testImplementation(libs.junit)
}
