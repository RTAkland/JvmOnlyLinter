/*
 * Copyright © 2025 RTAkland
 * Date: 15:37
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


package cn.rtast.jvmonly.linter.util

import cn.rtast.jvmonly.linter.JvmOnlyReportLevel
import org.jetbrains.kotlin.config.CompilerConfigurationKey

val DEVELOPMENT_MODE = CompilerConfigurationKey<Boolean>("developmentMode")
val REPORT_LEVEL = CompilerConfigurationKey<JvmOnlyReportLevel>("reportLevel")
val ENABLED = CompilerConfigurationKey<Boolean>("enabled")
val CUSTOM_ANNOTATION = CompilerConfigurationKey<String>("customAnnotation")
