plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    kapt("com.google.auto.service:auto-service:1.1.1")
    compileOnly(kotlin("compiler"))
    compileOnly("com.google.auto.service:auto-service-annotations:1.1.1")
    compileOnly(kotlin("stdlib"))
    implementation(project(":jvmonly-linter-runtime"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    archiveClassifier = ""
    exclude("kotlin/**")
    exclude("org/**")
    mergeServiceFiles()
}