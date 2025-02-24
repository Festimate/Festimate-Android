package com.mtc.convention

import com.android.build.api.dsl.CommonExtension
import com.mtc.convention.extension.debugImplementation
import com.mtc.convention.extension.getBundle
import com.mtc.convention.extension.getLibrary
import com.mtc.convention.extension.implementation
import com.mtc.convention.extension.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        extensions.getByType<ComposeCompilerGradlePluginExtension>().apply {
            enableStrongSkippingMode.set(true)
            includeSourceInformation.set(true)
        }
        dependencies {
            implementation(platform(libs.getLibrary("compose.bom")))
            implementation(libs.getBundle("compose"))
            debugImplementation(libs.getBundle("compose.debug"))
            implementation(libs.getLibrary("kotlinx.collections"))
        }
    }
}
