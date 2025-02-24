package com.mtc.convention

import com.android.build.api.dsl.CommonExtension
import com.mtc.convention.extension.getVersion
import com.mtc.convention.extension.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    pluginManager.apply("org.jetbrains.kotlin.android")

    commonExtension.apply {
        compileSdk = libs.getVersion("compileSdk").requiredVersion.toInt()

        defaultConfig {
            minSdk = libs.getVersion("minSdk").requiredVersion.toInt()

            vectorDrawables.useSupportLibrary = true
        }

        compileOptions {
            sourceCompatibility = Const.JAVA_VERSION
            targetCompatibility = Const.JAVA_VERSION
        }

        extensions.getByType<KotlinAndroidProjectExtension>().configureCompilerOptions()

        buildTypes {
            getByName("debug") {
                proguardFiles(
                    getDefaultProguardFile("proguard-android.txt"),
                    "proguard-debug.pro",
                )
            }

            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android.txt"),
                    "proguard-rules.pro",
                )
            }
        }
    }
}

fun KotlinAndroidProjectExtension.configureCompilerOptions() {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}
