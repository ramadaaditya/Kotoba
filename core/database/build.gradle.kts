plugins {
    id("composestartertemplate.android.library")
    id("composestartertemplate.android.hilt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.ramstudio.kotoba.core.database"
}

dependencies {
    implementation(Libs.roomRuntime)
    ksp(Libs.roomCompiler)
    implementation(Libs.roomKtx)
    
    implementation(project(":core:common"))
    implementation(Libs.androidCoreKtx)
}
