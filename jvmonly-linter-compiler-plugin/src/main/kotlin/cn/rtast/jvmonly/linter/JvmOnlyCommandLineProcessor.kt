/*
 * Copyright © 2025 RTAkland
 * Date: 15:32
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


@file:OptIn(ExperimentalCompilerApi::class)

package cn.rtast.jvmonly.linter

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration

@AutoService(CommandLineProcessor::class)
class JvmOnlyCommandLineProcessor : CommandLineProcessor {
    override val pluginId: String = "cn.rtast.jvmonly-linter"
    override val pluginOptions: Collection<AbstractCliOption> = listOf(
        CliOption(
            optionName = "developmentMode",
            valueDescription = "true or false",
            required = true,
            allowMultipleOccurrences = false,
            description = "是否开启开发模式 | Development mode"
        ),
        CliOption(
            optionName = "enabled",
            valueDescription = "true or false",
            required = true,
            allowMultipleOccurrences = false,
            description = "是否启用 | If enable the plugin"
        ),
        CliOption(
            optionName = "reportLevel",
            valueDescription = "ReportLevel.WARNING or ReportLevel.ERROR",
            description = "选择报告等级 | Compiler report level",
            allowMultipleOccurrences = false,
            required = true
        ),
        CliOption(
            optionName = "customAnnotation",
            valueDescription = "Custom annotation class reference",
            description = "自定义注解的引用 | Custom annotation reference"
        )
    )

    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) {
        when (option.optionName) {
            "developmentMode" -> configuration.put(DEVELOPMENT_MODE, value.toBoolean())
            "enabled" -> configuration.put(ENABLED, value.toBoolean())
            "reportLevel" -> configuration.put(REPORT_LEVEL, JvmOnlyReportLevel.fromString(value))
            "customAnnotation" -> configuration.put(CUSTOM_ANNOTATION, value.toString())
            else -> throw IllegalArgumentException("Unexpected config option ${option.optionName}")
        }
    }
}