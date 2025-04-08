plugins {
    kotlin("jvm")
    `java-gradle-plugin`
    `maven-publish`
    id("com.github.gmazzo.buildconfig")
}

dependencies {
    implementation(gradleApi())
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.20")
    implementation(project(":jvmonly-linter-runtime"))
}

buildConfig {
    val project = project(":jvmonly-linter-compiler-plugin")
    packageName(project.group.toString())
    buildConfigField("String", "KOTLIN_PLUGIN_VERSION", "\"${project.version}\"")
}

gradlePlugin {
    plugins {
        create("jvmonly-linter") {
            id = "cn.rtast.jvmonly-linter"
            implementationClass = "cn.rtast.jvmonly.linter.JvmOnlyLinterGradlePlugin"
        }
    }
}