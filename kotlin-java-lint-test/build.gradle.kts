plugins {
    kotlin("multiplatform")
}

kotlin {
    mingwX64()

    sourceSets {
        commonMain.dependencies {
            implementation("cn.rtast.kotlin-java-lint:runtime:0.0.1")
        }
    }
}

tasks.withType<AbstractPublishToMaven>().configureEach {
    onlyIf { false }
}

