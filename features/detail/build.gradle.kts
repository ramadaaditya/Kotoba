plugins {
    id("composestartertemplate.android.library")
    id("composestartertemplate.android.compose")
}

android {
    namespace = "com.ramstudio.kotoba.features.detail"
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
    
    implementation(Libs.androidCoreKtx)
    implementation(Libs.composeUi)
    implementation(Libs.composeMaterial3)
    implementation(Libs.nav3Runtime)
}
