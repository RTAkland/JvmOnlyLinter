plugins {
    kotlin("jvm") version "2.1.20" apply false
    kotlin("multiplatform") version "2.1.20" apply false
    kotlin("kapt") version "2.1.20" apply false
    id("maven-publish")
}

val pluginVersion: String by extra

subprojects {
    group = "cn.rtast.kotlin-java-lint"
    version = pluginVersion

    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://repo.maven.rtast.cn/releases")
    }

    apply(plugin = "maven-publish")

    publishing {
        repositories {
            mavenLocal()
//            maven("https://maven.rtast.cn/releases") {
//                credentials {
//                    username = "RTAkland"
//                    password = System.getenv("PUBLISH_TOKEN")
//                }
//            }
        }
    }
}