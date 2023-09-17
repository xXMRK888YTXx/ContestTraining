plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.xxmrk888ytxx.contesttraining"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.xxmrk888ytxx.contesttraining"
        minSdk = 24
        //noinspection EditedTargetSdkVersion
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.navigation:navigation-compose:2.7.2")

    //KTOR
    val ktor_version = "2.3.3"
    implementation("io.ktor:ktor-client-android:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-client-logging-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")

    //CameraX
    val camerax_version = "1.3.0-alpha06"
    implementation("androidx.camera:camera-core:${camerax_version}")
    implementation("androidx.camera:camera-camera2:${camerax_version}")
    implementation("androidx.camera:camera-lifecycle:${camerax_version}")
    implementation("androidx.camera:camera-video:${camerax_version}")
    implementation("androidx.camera:camera-view:${camerax_version}")
    implementation("androidx.camera:camera-extensions:${camerax_version}")



    //System UI controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.29.2-rc")

    //hilt
    implementation ("com.google.dagger:hilt-android:2.46")
    kapt ("com.google.dagger:hilt-compiler:2.46")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Glance
    implementation ("androidx.glance:glance-appwidget:1.0.0")

    //WorkManager
    implementation ("androidx.work:work-runtime-ktx:2.8.1")

    val room_version = "2.5.2"
    implementation ("androidx.room:room-runtime:$room_version")
    ksp ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")


    val latestAppCompatVersion =  "1.7.0-alpha03"
    implementation ("androidx.appcompat:appcompat:$latestAppCompatVersion")
    implementation ("androidx.appcompat:appcompat-resources:$latestAppCompatVersion")

    implementation ("com.google.mlkit:translate:17.0.1")

    implementation ("com.google.mlkit:text-recognition:16.0.0")



}