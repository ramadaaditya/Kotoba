import org.gradle.api.Plugin
import org.gradle.api.Project
import com.ramstudio.kotoba.convention.configureKotlinAndroid

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            configureKotlinAndroid()
            
            val android = extensions.getByName("android")
            val defaultConfigMethod = android.javaClass.getMethod("getDefaultConfig")
            val defaultConfig = defaultConfigMethod.invoke(android)
            val setTestInstrumentationRunnerMethod = defaultConfig.javaClass.getMethod("setTestInstrumentationRunner", java.lang.String::class.java)
            setTestInstrumentationRunnerMethod.invoke(defaultConfig, "androidx.test.runner.AndroidJUnitRunner")
        }
    }
}
