pluginManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
    }
}

rootProject.name = "kotlin-Java-Lint"

include(":kotlin-java-lint-compiler-plugin")
include(":kotlin-java-lint-gradle-plugin")
include(":kotlin-java-lint-runtime")
include(":kotlin-java-lint-test")
