import cn.rtast.jvmonly.linter.JvmOnlyReportLevel

plugins {
    kotlin("multiplatform")
    id("cn.rtast.jvmonly-linter") version "0.1.1"
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":jvmonly-linter-runtime"))
        }
    }
}

tasks.withType<AbstractPublishToMaven>().configureEach {
    onlyIf { false }
}

jvmOnly {
    enabled = true
//    developmentMode = true
    reportLevel = JvmOnlyReportLevel.WARNING
    customAnnotation = "test.TestAnn"
}