plugins {
    id("composestartertemplate.android.library")
    id("composestartertemplate.android.hilt")
}

android {
    namespace = "com.ramstudio.kotoba.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    
    implementation(Libs.androidCoreKtx)

    testImplementation(Libs.junit)
    testImplementation(Libs.kotlinxCoroutinesTest)
    testImplementation(Libs.mockitoKotlin)
}
