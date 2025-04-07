/*
 * Copyright © 2025 RTAkland
 * Date: 16:45
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


package cn.rtast.kotlin.java.lint

import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class KotlinJavaLintGradlePlugin : KotlinCompilerPluginSupportPlugin {
    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> {
        return kotlinCompilation.target.project.provider { listOf() }
    }

    override fun getCompilerPluginId(): String = "cn.rtast.kotlin-java-lint"

    override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
        groupId = "cn.rtast.kotlin-java-lint",
        artifactId = "kotlin-java-lint-compiler-plugin",
        version = "0.0.1"
    )

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean = true
}