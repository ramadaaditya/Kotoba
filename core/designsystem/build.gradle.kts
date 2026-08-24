plugins {
    id("composestartertemplate.android.library")
    id("composestartertemplate.android.compose")
}

android {
    namespace = "com.ramstudio.kotoba.core.designsystem"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(Libs.composeUi)
    implementation(Libs.composeMaterial3)
    implementation(Libs.androidCoreKtx)
}
