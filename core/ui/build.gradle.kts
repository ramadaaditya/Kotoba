plugins {
    id("composestartertemplate.android.library")
    id("composestartertemplate.android.compose")
}

android {
    namespace = "com.ramstudio.kotoba.core.ui"
}

dependencies {
    implementation(Libs.androidCoreKtx)
    implementation(Libs.composeUi)
    implementation(Libs.composeMaterial3)
}
