plugins {
    kotlin("jvm")
    kotlin("kapt")
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