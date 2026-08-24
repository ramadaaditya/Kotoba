pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Kotoba"
include(":app")

// Core Modules
include(":core:common")
include(":core:database")
include(":core:data")
include(":core:navigation")
include(":core:ui")
include(":core:designsystem")

// Feature Modules
include(":features:onboarding")
include(":features:kana")
include(":features:quiz")
include(":features:srs")
include(":features:reward")
