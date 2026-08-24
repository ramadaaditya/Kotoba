plugins {
    id("composestartertemplate.android.application")
    id("composestartertemplate.android.compose")
    id("composestartertemplate.android.hilt")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.ramstudio.kotoba"

    defaultConfig {
        applicationId = "com.ramstudio.kotoba"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation(project(":features:onboarding"))
    implementation(project(":features:kana"))
    implementation(project(":features:quiz"))
    implementation(project(":features:srs"))
    implementation(project(":features:reward"))

    implementation(Libs.activityCompose)
    implementation(Libs.composeMaterial3)
    implementation(Libs.composeMaterialIconsExtended)
    implementation(Libs.composeUi)
    implementation(Libs.composeUiGraphics)
    implementation(Libs.composeUiToolingPreview)
    implementation(Libs.androidCoreKtx)
    implementation(Libs.lifecycleRuntimeKtx)
    
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidxJunit)
    androidTestImplementation(Libs.espressoCore)
    
    debugImplementation(Libs.composeUiTooling)

    implementation(Libs.hiltNavigationCompose)

    implementation(Libs.retrofit)
    implementation(Libs.converterGson)
    implementation(platform(Libs.okhttpBom))
    implementation(Libs.okhttp)
    implementation(Libs.loggingInterceptor)

    implementation(Libs.nav3Runtime)
    implementation(Libs.nav3Ui)
    implementation(Libs.lifecycleViewmodelNav3)
    implementation(Libs.material3AdaptiveNav3)
    implementation(Libs.kotlinxSerializationCore)
}
