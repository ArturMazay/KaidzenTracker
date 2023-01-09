
plugins {
    id("com.android.application")
    id ("kotlin-kapt")
    kotlin("android")
}

android {
    namespace = "com.aistudio.kaidzentracker.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.aistudio.kaidzentracker.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("androidx.navigation:navigation-compose:2.5.3")
    implementation("com.himanshoe:charty:1.0.1")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation ("androidx.compose.material:material-icons-extended:1.3.1")

    implementation ("com.google.accompanist:accompanist-insets-ui:0.24.7-alpha")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    implementation( "io.insert-koin:koin-androidx-compose:3.2.2")
    implementation ("androidx.compose.runtime:runtime-livedata:1.4.0-alpha03")

    implementation ("com.chargemap.compose:numberpicker:1.0.3")
    implementation ("io.github.vanpra.compose-material-dialogs:core:0.8.1-rc")
    implementation ("dev.wirespec.jetmagic:jetmagic:1.4.0")

    kapt( "androidx.room:room-compiler:2.4.3")
    implementation ("androidx.room:room-runtime:2.4.3")
    implementation ("androidx.room:room-ktx:2.4.3")
    implementation("androidx.compose.ui:ui:1.3.2")
    implementation("androidx.compose.ui:ui-tooling:1.3.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.2")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")
}