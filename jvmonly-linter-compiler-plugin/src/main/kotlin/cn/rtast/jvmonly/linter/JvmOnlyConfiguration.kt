/*
 * Copyright © 2025 RTAkland
 * Date: 15:42
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


package cn.rtast.jvmonly.linter


data class JvmOnlyConfiguration(
    val developmentMode: Boolean,
    val enabled: Boolean,
    val reportLevel: JvmOnlyReportLevel
)