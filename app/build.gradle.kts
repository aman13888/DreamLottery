import org.gradle.internal.classpath.Instrumented.systemProperty

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.screenshot)
    alias(libs.plugins.ksp)
    kotlin("plugin.parcelize")

}

android {
    namespace = "com.dream.lottery"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dream.lottery"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        experimentalProperties["android.experimental.enableScreenshotTest"] = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.bundles.retrofit)
    implementation(libs.compose.constraintlayout)
    implementation(libs.compose.animation)
    implementation(libs.bundles.room)
    implementation(libs.hilt.navigation)
    implementation(libs.navigation.compose)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.ktx)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.android)
    ksp(libs.room.compiler)
    ksp(libs.hilt.compiler)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.test.core)
    testImplementation(libs.core.testing)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
    testImplementation("io.mockk:mockk:1.13.12")
}