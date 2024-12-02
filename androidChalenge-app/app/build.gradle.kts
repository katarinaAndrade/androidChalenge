plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = libs.versions.appModule.get()
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.appModule.get()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
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
    implementation(projects.core)
    implementation(projects.core.test)
    implementation(projects.design)
    implementation(projects.main.domain)
    implementation(projects.main.ui)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.appModuleBundles)

    testImplementation(libs.bundles.appModuleBundlesTestImpl)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.appModuleBundleAndroidTestImpl)

    debugImplementation(libs.bundles.appModuleBundlesDebugImpl)
}
