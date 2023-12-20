plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.devicechart"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.devicechart"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:protolite-well-known-types:18.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //GSON
    implementation ("com.google.code.gson:gson:2.10.1")

    //DataBinding
    implementation("androidx.databinding:databinding-runtime:8.2.0")

    //Glide and it's Annotation processor
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    //AvatarView
    implementation ("io.getstream:avatarview-coil:1.0.4")

    //MPAndroidChart
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

    //Kotlin
    implementation ("androidx.core:core-ktx:1.8.0")
    implementation ("org.jetbrains.kotlin:kotlin-bom:1.8.0")
}