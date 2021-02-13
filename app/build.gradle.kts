import Config.KEY_ALIAS
import Config.KEY_PASSWORD
import Config.STORE_FILE
import Config.STORE_PASSWORD

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")

    id("kotlin-parcelize")
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
    id("androidx.navigation.safeargs.kotlin")
}

android {

    val stringType = "String"

    compileSdkVersion(Apps.compileSdk)

    defaultConfig {
        applicationId = "com.mvvm.state.starter"
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            keyAlias = KEY_ALIAS
            keyPassword = KEY_PASSWORD
            storeFile = File(rootProject.projectDir.absolutePath + STORE_FILE)
            storePassword = STORE_PASSWORD
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    flavorDimensions("default") // A flavorDimension is a group of product flavors -> https://developer.android.com/studio/build/build-variants#product-flavors
    // each productFlavor must be belong to a flavor dimension
    // If you are using only one dimension, this property is optional,
    // and the plugin automatically assigns all the module's flavors to
    // that dimension.

    productFlavors {
        create("staging") {
            applicationIdSuffix = ".staging" // adding a suffix to app id, APKs with different appId IDs are treated as different apps -> https://developer.android.com/studio/build/application-id
            // If using multiple google-services.json, check https://stackoverflow.com/a/64183502/11276817
            buildConfigField(stringType, Config.BASE_URL, Config.STAGING_BASE_URL_VALUE)
        }

        create("production") {
            buildConfigField(stringType, Config.BASE_URL, Config.LIVE_BASE_URL_VALUE)
        }
    }

    buildTypes {

        getByName("debug") {
            isDebuggable = true
        }

        create("qc") {
            initWith(getByName("debug"))
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    testBuildType = "qc" // To make tests run using this specific Build Type (debug is the default)

    kotlinOptions {
        this.jvmTarget = "1.8"
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.30")

    // Kotlin core
    implementation(Libs.kotlin)
    implementation(Libs.appcompat)
    implementation(Libs.coreKtx)
    implementation(Libs.reflect)

    // Layout core
    implementation(Libs.constraintLayout)
    implementation(Libs.recyclerView)
    implementation(Libs.cardView)
    implementation(Libs.navigation)
    implementation(Libs.navigationFragment)
    implementation(Libs.paging)

    // Coroutines
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)
    api(Libs.coroutinesViewModelExt)

    // Koin
    implementation(Libs.koinAndroid)
    implementation(Libs.koinViewModel)
    implementation(Libs.koinAndroidScope)
    implementation(Libs.koinAndroidArc)

    // Network
    implementation(Libs.retrofit)
    implementation(Libs.retrofitGsonConverter)
    implementation(Libs.retrofitLoggingInterceptor)
    implementation(Libs.okhttp)
    implementation(Libs.gson)

    // Design
    implementation(Libs.material)
    implementation(Libs.coil)
    implementation(Libs.loadingView)
    implementation(Libs.scalableDp)
    implementation(Libs.bottomSheet)
    implementation(Libs.motionToast)

    // Logging/Debugging
    implementation(Libs.timber)
    implementation(Libs.prettyLogger)
    debugImplementation(Libs.leakCanary)

// Tests
    testImplementation(TestLibs.Unit.junit5)
    testImplementation(TestLibs.Unit.junit5KotlinExt)
    testImplementation(TestLibs.Unit.mockk)
    testImplementation(TestLibs.Unit.androidArchTestCore)
    androidTestImplementation(TestLibs.UI.androidEspresso)
    androidTestImplementation(TestLibs.UI.kaspresso)
    androidTestImplementation(TestLibs.UI.mockitoAndroid) // Using Mockito for UI tests instead of Mockk because of issue with APIs > 28 https://github.com/mockk/mockk/issues/297
    androidTestImplementation(TestLibs.UI.testRules)
    androidTestImplementation(TestLibs.UI.androidExtJunit)
    implementation(TestLibs.UI.fragmentTesting) { // Using debugImplementation doesn't work in other build variants https://stackoverflow.com/questions/61472757/how-do-you-run-a-fragment-test-in-a-release-build
        exclude(
            "androidx.test",
            "monitor"
        ) // Workaround for this issue https://github.com/android/android-test/issues/731
    }
}
