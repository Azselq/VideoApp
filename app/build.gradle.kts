plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.example.videoapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.videoapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        val key = property("apikey")?.toString() ?: error("Добавьте apikey в gradle.properties")
        buildConfigField("String","VIDEO_API_KEY", "\"$key\"")
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
    buildFeatures {
        compose = true
        buildConfig = true
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
    implementation(libs.androidx.material3)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.ui.test.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.mvikotlin.main)
    implementation(libs.mvikotlin.core)
    implementation(libs.mvikotlin.coroutines)
    implementation(libs.decompose.core)
    implementation(libs.decompose.jetpack)
    implementation(libs.room.core)
    implementation(libs.dagger.core)
    implementation(libs.glide.compose)
    implementation(libs.retorfit.core)
    implementation(libs.retorfit.gsonConverter)
    implementation(libs.icons)
    ksp(libs.room.compiler)
    ksp(libs.dagger.compiler)
    implementation (libs.accompanist.swiperefresh)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)


}