/*
 * Copyright © 2025 RTAkland
 * Date: 16:45
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */

package cn.rtast.jvmonly.linter

import cn.rtast.`jvmonly-linter`.BuildConfig
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class JvmOnlyLinterGradlePlugin : KotlinCompilerPluginSupportPlugin {
    private var gradleExtension = JvmOnlyGradleExtension()
    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> {
        kotlinCompilation.dependencies {
            api("cn.rtast.jvmonly-linter:jvmonly-linter-runtime:${BuildConfig.KOTLIN_PLUGIN_VERSION}")
        }
        gradleExtension = kotlinCompilation.target.project.extensions.findByType(JvmOnlyGradleExtension::class.java)
            ?: JvmOnlyGradleExtension()
        return kotlinCompilation.target.project.provider {
            listOf(
                SubpluginOption("enabled", gradleExtension.enabled.toString()),
                SubpluginOption("developmentMode", gradleExtension.developmentMode.toString()),
                SubpluginOption("reportLevel", gradleExtension.reportLevel.name),
                SubpluginOption("customAnnotation", gradleExtension.customAnnotation),
            )
        }
    }

    override fun getCompilerPluginId(): String = "cn.rtast.jvmonly-linter"

    override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
        groupId = "cn.rtast.jvmonly-linter",
        artifactId = "jvmonly-linter-compiler-plugin",
        version = BuildConfig.KOTLIN_PLUGIN_VERSION
    )

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean = true

    override fun apply(target: Project) {
        target.extensions.create("jvmOnly", JvmOnlyGradleExtension::class.java)
        super.apply(target)
    }
}