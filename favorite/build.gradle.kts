plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
}
android {
    namespace = "com.example.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures{
        viewBinding = true

    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":app"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Room
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.rxjava3) // Room with RxJava3 support
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // CardView & RecyclerView
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.recyclerview)

    // Navigation Fragment & UI
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.navigation.ui)

    // Retrofit & Okhttp
    implementation(libs.com.squareup.retrofit2)
    implementation(libs.com.squareup.retrofit2.converter)
    implementation(libs.com.squareup.okhttp3)

    // RxJava3

    implementation("io.reactivex.rxjava3:rxjava:3.1.9") // RxJava3 core
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2") // RxAndroid
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")  // Retrofit RxJava3 Adapter
    implementation("androidx.room:room-rxjava3:2.6.1")

    // Retrofit adapter for RxJava3

    // Chucker interceptor
    debugImplementation(libs.com.github.chuckerteam.chucker)
    releaseImplementation(libs.com.github.chuckerteam.chucker.release)

    // Shimmer
    implementation(libs.com.facebook.shimmer)

    // Lottie
    implementation(libs.com.airbnb.android)

    // Datastore
    implementation(libs.androidx.datastore)

    // Circle Image
    implementation(libs.de.hdodenhof)

    // LiveData, ViewModel, & Activity KTX
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.activity)

    // Glide
    implementation(libs.com.github.bumptech.glide)
    annotationProcessor(libs.com.github.bumptech.glide.compiler)

    // Dagger hilt
    implementation(libs.com.google.dagger)
    kapt("com.google.dagger:hilt-compiler:2.48")

    // Coroutines (if needed)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.0")

    // Room with RxJava3 support
    implementation("androidx.room:room-rxjava3:2.5.0")

    // OkHttp logging interceptor (for debugging network requests)
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

}