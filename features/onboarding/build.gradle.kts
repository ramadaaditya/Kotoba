plugins {
    id("composestartertemplate.android.library")
    id("composestartertemplate.android.compose")
    id("composestartertemplate.android.hilt")
}

android {
    namespace = "com.ramstudio.kotoba.features.onboarding"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))

    implementation(Libs.lifecycleViewmodelKtx)
    implementation(Libs.hiltNavigationCompose)
    implementation(Libs.nav3Runtime)
    implementation(Libs.composeUi)
    implementation(Libs.composeMaterial3)
}
