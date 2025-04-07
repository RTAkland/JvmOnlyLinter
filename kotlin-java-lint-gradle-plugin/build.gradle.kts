plugins {
    kotlin("jvm")
    `java-gradle-plugin`
    `maven-publish`
}

dependencies {
    implementation(gradleApi())
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.20")
}

gradlePlugin {
    plugins {
        create("kotlin-java-lint") {
            id = "cn.rtast.kotlin-java-lint"
            implementationClass = "cn.rtast.kotlin.java.lint.KotlinJavaLintGradlePlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}