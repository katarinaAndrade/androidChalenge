plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = libs.versions.mainUIModule.get()
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
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

    val java = JavaVersion.toVersion(libs.versions.javaVersion.get())
    compileOptions {
        sourceCompatibility = java
        targetCompatibility = java
    }
    kotlinOptions {
        jvmTarget = java.toString()
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.design)
    implementation(projects.main.domain)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.mainUIModuleBundles)

    testImplementation(libs.bundles.mainUIModuleBundlesTestImpl)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.mainUIModuleBundleAndroidTestImpl)

    debugImplementation(libs.bundles.mainUIModuleBundlesDebugImpl)
}
