pluginManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
    }
}

rootProject.name = "jvmonly-linter"

include(":jvmonly-linter-compiler-plugin")
include(":jvmonly-linter-gradle-plugin")
include(":jvmonly-linter-runtime")
include(":jvmonly-linter-test")
