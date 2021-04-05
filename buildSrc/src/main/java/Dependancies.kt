object Apps {
    const val compileSdk = 28
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val kotlin = "1.4.20"
    const val appcompat = "1.1.0"
    const val coreKtx = "1.2.0"
    const val constraintLayout = "2.0.2"
    const val navigation = "2.3.3"
    const val coroutines = "1.3.7"
    const val room = "2.2.4"
    const val koin = "2.0.1"
    const val retrofit = "2.7.1"
    const val timber = "4.7.1"
    const val prettyLogger = "logger:2.2.0"
    const val gson = "2.8.6"
    const val okHttp = "3.9.0"
    const val cookieBar = "1.1.4"
    const val motionToast = "1.3.3.4"
    const val glide = "4.10.0"
    const val ktLint = "9.4.1"
    const val detekt = "1.14.2"
    const val scalableDimensions = "1.0.6"
    const val coil = "1.0.0"
    const val loadingView = "1.1"
    const val material = "1.3.0-alpha03"
    const val shimmerEffect = "0.5.0"
    const val prettyTime = "4.0.1.Final"
    const val bottomSheet = "2.0.0"
    const val dateRangePicker = "2.0.0"
    const val monthYearPicker = "1.3.0"
    const val leakCanary = "2.5"
    const val googleServices = "4.3.4"
    const val crashlytics = "2.4.1"
    const val firebaseBoM = "26.2.0"
    const val junit4 = "4.12"
    const val junit5 = "5.7.0"
    const val junit5KotlinExt = "0.0.1"
    const val androidExtJunit = "1.1.0"
    const val androidEspresso = "3.2.0"
    const val mockk = "1.10.0"
    const val archTestCore = "2.1.0"
    const val coroutineTest = "1.4.2"
    const val kaspresso = "1.2.0"
    const val testRules = "1.1.0"
    const val mockitoAndroid = "3.7.0"
    const val fragmentTesting = "1.2.5"
    const val navigationTesting = "2.3.1"
}

object Libs {
    // Kotlin core
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    // Layout core
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.appcompat}"
    const val cardView = "androidx.cardview:cardview:1.0.0"
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val paging = "androidx.paging:paging-runtime:2.0.0"

    // Coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesViewModelExt = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0-beta01"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRoomKtx = "androidx.room:room-ktx:${Versions.room}"

    // Koin
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinAndroidScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinAndroidArc = "org.koin:koin-androidx-ext:${Versions.koin}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofitLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.9.1"

    // Network
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    // Design
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val shimmerEffect = "com.facebook.shimmer:shimmer:${Versions.shimmerEffect}"
    const val cookieBar = "org.aviran.cookiebar2:cookiebar2:${Versions.cookieBar}"
    const val motionToast = "com.github.Spikeysanju:MotionToast:${Versions.motionToast}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val loadingView = "com.samigehi:loadingview:${Versions.loadingView}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val scalableDp = "com.intuit.sdp:sdp-android:${Versions.scalableDimensions}"
    const val prettyTime = "org.ocpsoft.prettytime:prettytime:${Versions.prettyTime}"
    const val bottomSheet = "com.github.andrefrsousa:SuperBottomSheet:${Versions.bottomSheet}"
    const val dateRangePicker = "com.archit.calendar:awesome-calendar:${Versions.dateRangePicker}"
    const val monthYearPicker = "com.whiteelephant:monthandyearpicker:${Versions.monthYearPicker}"

    // Firebase
    const val firebaseBoM = "com.google.firebase:firebase-bom:${Versions.firebaseBoM}"
    const val dynamicLinks = "com.google.firebase:firebase-dynamic-links-ktx"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    // Logging/Debug
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val prettyLogger = "com.orhanobut:${Versions.prettyLogger}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
}

object TestLibs {
    object Unit {
        const val junit4 = "junit:junit:${Versions.junit4}"
        const val junit5 = "org.junit.jupiter:junit-jupiter:${Versions.junit5}"
        const val junit5KotlinExt = "de.jodamob.junit5:junit5-kotlin:${Versions.junit5KotlinExt}"
        const val coroutineTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val androidArchTestCore = "android.arch.core:core-testing:${Versions.archTestCore}"
    }

    object UI {
        const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoAndroid}"
        const val androidEspresso =
            "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"
        const val kaspresso = "com.kaspersky.android-components:kaspresso:${Versions.kaspresso}"
        const val testRules = "androidx.test:rules:${Versions.testRules}"
        const val androidExtJunit = "androidx.test.ext:junit:${Versions.androidExtJunit}"
        const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"
        const val navigationTesting = "androidx.navigation:navigation-testing:${Versions.navigationTesting}"
    }
}

object Config {
    //Api configs
    const val BASE_URL = "BASE_URL"

    const val STAGING_BASE_URL_VALUE = "\"https://api.covid19api.com/\""
    const val LIVE_BASE_URL_VALUE = "\"https://api.covid19api.com/\""

    //Signing configs
    const val KEY_ALIAS = ""
    const val KEY_PASSWORD = ""
    const val STORE_PASSWORD = ""
    const val STORE_FILE = "/keystore/key_store.jks"
}