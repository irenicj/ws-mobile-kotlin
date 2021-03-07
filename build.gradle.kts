plugins {
    kotlin("multiplatform") version "1.4.31"
    id("com.android.library")
    id("kotlin-android-extensions")
}


repositories {
    google()
    jcenter()
    mavenCentral()
}

kotlin {
    android()
    iosX64("ios") {
        binaries {
            framework {
                baseName = "library"
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                // anko for background post req
                implementation("org.jetbrains.anko:anko-commons:0.10.0")


                //OkHttp
                implementation("com.squareup.okhttp3:okhttp:4.9.0")

                //OkHttp MockWeb Server for testing
//    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.0")

                //Gson for type converter kotlin
                implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")


                //Retrofit for HTTP calls
                implementation("com.squareup.retrofit2:retrofit:2.9.0")

                // Align versions of all Kotlin components
//                implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

                // Use the Kotlin JDK 8 standard library.
                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

                // Google Play Services - Location services
                implementation("com.google.android.gms:play-services-location:18.0.0")

                // Use the Kotlin test library.
                implementation("org.jetbrains.kotlin:kotlin-test")

                // Use the Kotlin JUnit integration.
                implementation("org.jetbrains.kotlin:kotlin-test-junit")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
    }
}