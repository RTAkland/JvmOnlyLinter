plugins {
    kotlin("multiplatform")
    id("cn.rtast.kotlin-java-lint") version "0.0.1"
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation("cn.rtast.kotlin-java-lint:runtime:0.0.1")
        }
    }
}

tasks.withType<AbstractPublishToMaven>().configureEach {
    onlyIf { false }
}

