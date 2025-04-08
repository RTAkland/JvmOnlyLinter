@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
}

kotlin {
    withSourcesJar()
    linuxX64()
    linuxArm64()
    macosArm64()
    macosX64()
    mingwX64()
    js(IR) {
        browser()
        nodejs()
    }
    wasmJs {
        browser()
        nodejs()
    }
    jvm {
        compilerOptions.jvmTarget = JvmTarget.JVM_1_8
    }
}