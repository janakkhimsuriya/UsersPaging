plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
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

    buildFeatures {
        dataBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Android Support Dependencies
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)

    // Kotlin Support Dependencies
    implementation(Dependencies.kotlinCore)
    implementation(Dependencies.kotlinActivity)
    implementation(Dependencies.kotlinFragment)

    // Dagger Hilt ViewModel Support Dependencies
    implementation(Dependencies.hiltViewModel)
    kapt(Dependencies.hiltViewModelCompiler)

    // Retrofit Support Dependencies
    implementation(Dependencies.retrofit)

    // Paging Support Dependencies
    implementation(Dependencies.paging)
}