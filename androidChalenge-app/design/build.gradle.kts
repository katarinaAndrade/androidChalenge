plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = libs.versions.designModule.get()
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
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.designModuleBundles)

    testImplementation(libs.bundles.designModuleBundlesTestImpl)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.designModuleBundleAndroidTestImpl)

    debugImplementation(libs.bundles.designModuleBundlesDebugImpl)
}
