import java.util.Properties

// 读取 local.properties
val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}

val githubUser = localProperties.getProperty("gpr.user")
val githubToken = localProperties.getProperty("gpr.token")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = "com.kirincore.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.kirincore.core"
                artifactId = "kirin-core"
                version = "1.0.0"
            }
        }
        // 添加仓库配置
        repositories {
            maven {
                name = "KirinCore"
                // 替换为你的实际仓库路径
                url = uri("https://maven.pkg.github.com/MeDeity/KirinCore")
                credentials {
                    username = githubUser ?: System.getenv("GPR_USER")
                    password = githubToken ?: System.getenv("GPR_TOKEN")
                }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}