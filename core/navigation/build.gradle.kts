plugins {
    id("composestartertemplate.android.library")
    id("composestartertemplate.android.compose")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.ramstudio.kotoba.core.navigation"
}

dependencies {
    implementation(Libs.androidCoreKtx)
    implementation(Libs.composeUi)
    implementation(Libs.composeMaterial3)
    implementation(Libs.composeMaterialIconsExtended)
    implementation(Libs.kotlinxSerializationCore)
    implementation(Libs.nav3Runtime)
}
