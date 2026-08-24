import org.gradle.api.Plugin
import org.gradle.api.Project
import com.ramstudio.kotoba.convention.configureKotlinAndroid

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            configureKotlinAndroid()
            
            val android = extensions.getByName("android")
            val defaultConfigMethod = android.javaClass.getMethod("getDefaultConfig")
            val defaultConfig = defaultConfigMethod.invoke(android)
            val setTargetSdkMethod = defaultConfig.javaClass.getMethod("setTargetSdk", java.lang.Integer::class.java)
            setTargetSdkMethod.invoke(defaultConfig, 36)
        }
    }
}
