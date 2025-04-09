plugins {
    id("com.android.application")
    id("com.google.gms.google-services") // Firebase plugin
}

android {
    namespace = "com.example.megamart"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.megamart"
        minSdk = 31
        //noinspection OldTargetApi
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.drawerlayout)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.auth)

    // Additional dependencies
    implementation(libs.lottie)
    implementation(libs.android.async.http)
    implementation(libs.circleimageview)
    implementation(libs.core)
    implementation(libs.firebase.firestore)
    implementation(libs.volley)
    implementation(libs.retrofit)
    implementation(libs.squareup.converter.gson)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.squareup.picasso)
    implementation(libs.firebase.database.v2100)
    implementation(libs.firebase.storage)
    implementation(libs.com.github.bumptech.glide.glide)
    implementation(libs.support.annotations)
    annotationProcessor(libs.compiler.v4160)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.core)
    implementation(libs.firebase.firestore)
    implementation(libs.volley)
    implementation(libs.retrofit)
    implementation(libs.squareup.converter.gson)
    implementation(platform(libs.firebase.bom.v33110))
    implementation(libs.google.firebase.analytics)
    implementation(libs.squareup.picasso)
    implementation(libs.firebase.database.v2100)
    implementation(libs.firebase.storage)
    implementation(libs.com.github.bumptech.glide.glide)
    annotationProcessor(libs.glide.compiler)
    implementation(libs.android.async.http)
    implementation(libs.circleimageview)
    implementation(libs.core)
    implementation(libs.firebase.firestore)
    implementation(libs.volley)
    implementation(libs.retrofit)
    implementation(libs.squareup.converter.gson)
    implementation(platform(libs.firebase.bom))
    implementation(libs.com.google.firebase.firebase.analytics)
    implementation(libs.picasso.v28)
    implementation(libs.com.google.firebase.firebase.database)
    implementation(libs.firebase.storage)
    implementation(libs.com.github.bumptech.glide.glide)
    annotationProcessor(libs.compiler.v4160)
   implementation(libs.recyclerview)
    implementation(libs.viewpager2)
    //noinspection UseTomlInstead


}
