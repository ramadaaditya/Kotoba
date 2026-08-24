package com.ramstudio.kotoba.convention

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.configureKotlinAndroid() {
    val android = extensions.getByName("android")
    
    // Use reflection to avoid type parameter issues in build-logic
    val compileSdkMethod = android.javaClass.getMethod("setCompileSdk", java.lang.Integer::class.java)
    compileSdkMethod.invoke(android, 37)

    val defaultConfigMethod = android.javaClass.getMethod("getDefaultConfig")
    val defaultConfig = defaultConfigMethod.invoke(android)
    val setMinSdkMethod = defaultConfig.javaClass.getMethod("setMinSdk", java.lang.Integer::class.java)
    setMinSdkMethod.invoke(defaultConfig, 24)

    val compileOptionsMethod = android.javaClass.getMethod("getCompileOptions")
    val compileOptions = compileOptionsMethod.invoke(android)
    val setSourceCompatibilityMethod = compileOptions.javaClass.getMethod("setSourceCompatibility", JavaVersion::class.java)
    val setTargetCompatibilityMethod = compileOptions.javaClass.getMethod("setTargetCompatibility", JavaVersion::class.java)
    setSourceCompatibilityMethod.invoke(compileOptions, JavaVersion.VERSION_11)
    setTargetCompatibilityMethod.invoke(compileOptions, JavaVersion.VERSION_11)

    val setCoreLibraryDesugaringEnabledMethod = compileOptions.javaClass.getMethod("setCoreLibraryDesugaringEnabled", Boolean::class.javaPrimitiveType)
    setCoreLibraryDesugaringEnabledMethod.invoke(compileOptions, true)

    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android-desugarJdkLibs").get())
    }
}

internal fun Project.configureCompose() {
    val android = extensions.getByName("android")
    
    val buildFeaturesMethod = android.javaClass.getMethod("getBuildFeatures")
    val buildFeatures = buildFeaturesMethod.invoke(android)
    val setComposeMethod = buildFeatures.javaClass.getMethod("setCompose", java.lang.Boolean::class.java)
    setComposeMethod.invoke(buildFeatures, true)

    dependencies {
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))
    }
}
