plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = libs.versions.mainDomainModule.get()
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
    implementation(libs.bundles.mainDomainModuleBundles)
    testImplementation(libs.bundles.mainDomainModuleBundlesTestImpl)
    androidTestImplementation(libs.bundles.mainDomainModuleBundleAndroidTestImpl)
    debugImplementation(libs.bundles.mainDomainModuleBundlesDebugImpl)
}
