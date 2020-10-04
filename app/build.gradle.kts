import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {

    val stringType = "String"

    compileSdkVersion(Apps.compileSdk)

    defaultConfig {
        applicationId = "com.ibtikar.mvvm_starter_koin_coroutines"
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        getByName("debug") {
            buildConfigField(stringType, Config.BASE_URL_KEY, Config.BASE_URL_VALUE)
            isDebuggable = true
        }

        getByName("release") {

            buildConfigField(stringType, Config.BASE_URL_KEY, Config.BASE_URL_VALUE)

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        // We have to add the explicit cast before accessing the options itself.
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    dataBinding.isEnabled = true
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))

    implementation(Libs.appcompat)
    implementation(Libs.appcompat)
    implementation(Libs.coreKtx)
    implementation(Libs.constraintLayout)
    implementation(Libs.recyclerView)
    implementation(Libs.imageLibrary)
    implementation(Libs.cardView)
    implementation(Libs.loadingView)
    implementation(Libs.navigation)
    implementation(Libs.navigationFragment)
    implementation(Libs.paging)

    //coroutines
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)
    api(Libs.coroutinesViewModelExt)

    //koin
    implementation(Libs.koinAndroid)
    implementation(Libs.koinViewModel)
    implementation(Libs.koinAndroidScope)
    implementation(Libs.koinAndroidArc)

    //network
    implementation(Libs.retrofit)
    implementation(Libs.retrofitGsonConverter)
    implementation(Libs.retrofitLoggingInterceptor)
    implementation(Libs.okhttp)
    implementation(Libs.gson)
    implementation("androidx.room:room-runtime:2.2.5")
    implementation(Libs.glide)

    //test
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.mockk)
    androidTestImplementation(TestLibs.androidExtJunit)
    androidTestImplementation(TestLibs.androidEspresso)
    testImplementation(TestLibs.androidArchTestCore)
    annotationProcessor("androidx.room:room-compiler:2.2.5")
}