plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", AppConfig.baseApiUrl)
            buildConfigField("String", "API_KEY", AppConfig.apiKey)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", AppConfig.baseApiUrl)
            buildConfigField("String", "API_KEY", AppConfig.apiKey)
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
    // Base Module
    implementation(project(":base"))

    // Unit Testing Dependencies
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitTest)
    androidTestImplementation(Dependencies.espressoTest)

    // Android Support Dependencies
    implementation(Dependencies.material)
    implementation(Dependencies.appcompat)
    implementation(Dependencies.constraint)
    implementation(Dependencies.swipeRefresh)

    // Kotlin KTX Support Dependencies
    implementation(Dependencies.kotlinCore)
    implementation(Dependencies.kotlinActivity)
    implementation(Dependencies.kotlinFragment)

    // Navigation Support Dependencies
    implementation(Dependencies.fragmentKtx)
    //implementation(Dependencies.arguments)
    implementation(Dependencies.uiKtx)

    // Dagger Hilt Support Dependencies
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)

    // Dagger Hilt ViewModel Support Dependencies
    implementation(Dependencies.hiltViewModel)
    kapt(Dependencies.hiltViewModelCompiler)

    // OkHttp Support Dependencies
    implementation(Dependencies.curl)
    implementation(Dependencies.gson)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.logging)

    // Retrofit Support Dependencies
    implementation(Dependencies.retrofit)
    implementation(Dependencies.convertor)

    // Paging Support Dependencies
    implementation(Dependencies.paging)

    // Room DB Support Dependencies
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)


    implementation("com.facebook.stetho:stetho:1.6.0")
}