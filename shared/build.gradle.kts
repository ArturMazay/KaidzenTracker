plugins {
    id("com.android.application")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    android()


    // Workaround for an issue:
    //    https://youtrack.jetbrains.com/issue/KT-53561/Invalid-LLVM-module-inlinable-function-call-in-a-function-with-debug-info-must-have-a-dbg-location
    // Compose compiler produces nodes without line information sometimes that provokes Kotlin native compiler to report errors.
    // TODO: remove workaround when switch to Kotlin 1.8
    val disableKonanVerification = "-Xverify-compiler=false"

    iosX64("uikitX64") {
        binaries {
            executable() {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics",
                    disableKonanVerification
                )
            }
        }
    }
    iosArm64("uikitArm64") {
        binaries {
            executable() {
                entryPoint = "main"
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics",
                    disableKonanVerification
                )
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {


                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)

            dependencies {
                implementation("androidx.appcompat:appcompat:1.5.1")
                implementation("androidx.activity:activity-compose:1.5.0")
            }
        }





        val nativeMain by creating {
            dependsOn(commonMain)
        }


        val uikitMain by creating {
            dependsOn(nativeMain)
        }
        val uikitX64Main by getting {
            dependsOn(uikitMain)
        }
        val uikitArm64Main by getting {
            dependsOn(uikitMain)
        }
    }
}
compose.experimental {

    uikit.application {
        bundleIdPrefix = "KaidzenTracker"
        projectName = "KaidzenTracker"
        deployConfigurations {


            simulator("IPhone11") {
                //Usage: ./gradlew iosDeployIPhone11Debug
                device = org.jetbrains.compose.experimental.dsl.IOSDevices.IPHONE_11_PRO
            }

            connectedDevice("Device") {
                //First need specify your teamId here, or in local.properties (compose.ios.teamId=***)
                //teamId="***"
                //Usage: ./gradlew iosDeployDeviceRelease
            }
        }
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}


android {
    namespace = "com.aistudio.kaidzentracker"
    compileSdk = 32
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
        }
    }
}
