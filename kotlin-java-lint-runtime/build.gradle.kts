import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm")
}

tasks.compileKotlin {
    compilerOptions.jvmTarget = JvmTarget.JVM_1_8
}

tasks.compileJava {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
            artifactId = "runtime"
        }
    }
}