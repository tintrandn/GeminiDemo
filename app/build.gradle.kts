import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.aitripdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.aitripdemo"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val localProperties = Properties()
        val localFile = rootProject.file("local.properties")

        if (localFile.exists()) {
            FileInputStream(localFile).use { localProperties.load(it) }
        }

        val apiKey = localProperties.getProperty("API_KEY", "")
        buildConfigField("String", "API_KEY", "\"$apiKey\"")

        val unsKey = localProperties.getProperty("UNS_KEY", "")
        buildConfigField("String", "UNS_KEY", "\"$unsKey\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidSupportAppCompat)
    implementation(libs.androidSupportCardView)
    implementation(libs.androidSupportGridLayout)
    implementation(libs.androidSupportDesign)
    implementation(libs.androidSupportAnnotations)
    implementation(libs.activityKtx)
    implementation(libs.corektx)
    implementation(libs.vectordrawable)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.rxBindingSupport)
    implementation(libs.rxBindingAppCompat)
    implementation(libs.rxBindingRecyclerView)
    implementation(libs.rxBinding)
    implementation(libs.rxAndroid)
    implementation(libs.rxJava)
    implementation(libs.threeTenAbp)
    implementation(libs.dagger)
    implementation(libs.daggerAndroid)
    implementation(libs.lifecycleViewModel)
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.shimmer)
    implementation(libs.generativeai)
    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)
    implementation(libs.lottie)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)
    implementation(libs.glide)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    kapt(libs.daggerCompiler)
    kapt(libs.daggerAndroidProcessor)
    kapt(libs.glideComplier)
}