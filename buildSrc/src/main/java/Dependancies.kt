object Apps {
    const val compileSdk = 28
    const val minSdk = 21
    const val targetSdk = 28
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "3.5.3"
    const val kotlin = "1.3.61"
    const val appcompat = "1.1.0"
    const val coreKtxVersion = "1.2.0"
    const val constraintLayoutVersion = "1.1.3"
    const val coroutinesVersion = "1.3.7"
    const val roomVersion = "2.2.4"
    const val koinVersion = "2.0.1"
    const val retrofitVersion = "2.7.1"

    /* test */
    const val junit = "4.12"
    const val androidExtJunit = "1.1.0"
    const val androidEspresso = "3.2.0"
    const val mockkVersion = "1.10.0"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.appcompat}"
    const val cardView = "androidx.cardview:cardview:1.0.0"
    const val imageLibrary = "io.coil-kt:coil:0.11.0"
    const val loadingView = "com.samigehi:loadingview:1.1"

    // coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    const val coroutinesViewModelExt = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0-beta01"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomRoomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"

    // Koin
    const val koinAndroid = "org.koin:koin-android:${Versions.koinVersion}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koinVersion}"
    const val koinAndroidScope = "org.koin:koin-androidx-scope:${Versions.koinVersion}"
    const val koinAndroidArc = "org.koin:koin-androidx-ext:${Versions.koinVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    const val retrofitLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.9.1"
    const val gson = "com.google.code.gson:gson:2.4"
    const val okhttp = "com.squareup.okhttp3:okhttp:3.9.0"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidExtJunit = "androidx.test.ext:junit:${Versions.androidExtJunit}"
    const val androidEspresso = "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"
    const val mockk = "io.mockk:mockk:${Versions.mockkVersion}"
    const val androidArchTestCore = "android.arch.core:core-testing:2.1.0"
}

object Config {
    const val BASE_URL_KEY = "BASE_URL"
    const val BASE_URL_VALUE = "\"https://www.google.com\""
}