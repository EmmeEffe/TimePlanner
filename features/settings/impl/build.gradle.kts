/*
 * Copyright 2023 Stanislav Aleshin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20"
    kotlin("plugin.serialization") version "1.8.21"
    kotlin("kapt")
}

repositories {
    mavenCentral()
    google()
}

android {
    namespace = "ru.aleshin.features.settings.impl"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion

        testInstrumentationRunner = Config.testInstrumentRunner
        consumerProguardFiles(Config.consumerProguardFiles)

        buildConfigField("String", "VERSION_NAME", "\"${Config.versionName}\"")
        buildConfigField("String", "VERSION_CODE", "\"${Config.versionCode}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.kotlinCompiler
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":module-injector"))
    implementation(project(":core:utils"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))

    implementation(project(":features:settings:api"))

    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.material)
    implementation(Dependencies.AndroidX.lifecycleRuntime)
    implementation(Dependencies.AndroidX.serialization)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.activity)

    implementation(Dependencies.Dagger.core)
    kapt(Dependencies.Dagger.kapt)

    implementation(Dependencies.Voyager.navigator)
    implementation(Dependencies.Voyager.screenModel)

    testImplementation(Dependencies.Test.jUnit)
    testImplementation(Dependencies.Test.turbine)
    androidTestImplementation(Dependencies.Test.jUnitExt)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Test.composeJUnit)
    debugImplementation(Dependencies.Compose.uiTooling)
    debugImplementation(Dependencies.Compose.uiTestManifest)
}
