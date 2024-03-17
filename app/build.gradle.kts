plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") version "1.9.0"
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android") version "2.46" apply false
    id ("dagger.hilt.android.plugin")
}


android {
    namespace = "com.example.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 28
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

val coil = "2.6.0"
val retrofit = "2.9.0"
val okHttp = "4.12.0"
val media3 = "1.2.1"
val exoplayerExoPlayer = "2.19.1"
val ygorluizfrazao = "1.0.0-alpha01"
val daggerHilt = "2.50"
val hiltCompose = "1.1.0"
val room = "2.6.1"
val navigationCompose = "2.7.7"
val material = "1.6.1"
val coreKtx = "1.12.0"
val runtimeKtx = "2.7.0"
val activityCompose = "1.8.2"
val composeBom = "2023.08.00"
val junit = "4.13.2"
val extJunit = "1.1.5"
val espressoCore = "3.5.1"

dependencies {
    //Coil
    implementation("io.coil-kt:coil-compose:$coil")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofit")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit")
    implementation ("com.squareup.okhttp3:okhttp:$okHttp")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okHttp")

    //Media3 + Exoplayer
    implementation ("androidx.media3:media3-exoplayer:$media3")
    implementation ("androidx.media3:media3-ui:$media3")
    implementation ("com.google.android.exoplayer:exoplayer:$exoplayerExoPlayer")

    //Library to convert Icons from drawable
    implementation ("com.github.ygorluizfrazao:compose-resources:$ygorluizfrazao")

    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:$daggerHilt")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltCompose")
    kapt ("com.google.dagger:hilt-compiler:$daggerHilt")
    kapt ("androidx.hilt:hilt-compiler:$hiltCompose")

    //ROOM
    implementation("androidx.room:room-runtime:$room")
    annotationProcessor("androidx.room:room-compiler:$room")
    ksp("androidx.room:room-compiler:$room")
    implementation("androidx.room:room-ktx:$room")

    //Navigation
    implementation("androidx.navigation:navigation-compose:$navigationCompose")
    implementation ("androidx.compose.material:material:$material")

    implementation("androidx.core:core-ktx:$coreKtx")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$runtimeKtx")
    implementation("androidx.activity:activity-compose:$activityCompose")
    implementation(platform("androidx.compose:compose-bom:$composeBom"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:$junit")
    androidTestImplementation("androidx.test.ext:junit:$extJunit")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoCore")
    androidTestImplementation(platform("androidx.compose:compose-bom:$composeBom"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

