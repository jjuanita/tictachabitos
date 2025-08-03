plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.tictachabits"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tictachabits"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
}

dependencies {

    implementation(libs.play.services.wearable)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.wear.tooling.preview)
    implementation(libs.activity.compose)
    implementation(libs.core.splashscreen)
    implementation("androidx.wear:wear:1.2.0")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation(libs.navigation.runtime.android)
    implementation ("androidx.wear.compose:compose-material:1.2.1")
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation ("androidx.activity:activity-compose:1.9.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")



    // Jetpack Compose base (por si falta)
    implementation ("androidx.compose.ui:ui:1.6.4")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.4")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.4")
    implementation("androidx.compose.animation:animation:1.6.4")
    implementation(libs.material)


    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}