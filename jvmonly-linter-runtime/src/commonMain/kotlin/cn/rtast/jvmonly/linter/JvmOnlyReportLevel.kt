/*
 * Copyright © 2025 RTAkland
 * Date: 15:48
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


package cn.rtast.jvmonly.linter

enum class JvmOnlyReportLevel {
    WARNING, ERROR, NONE;

    companion object {
        fun fromString(string: String): JvmOnlyReportLevel {
            return when (string) {
                "WARNING" -> WARNING
                "ERROR" -> ERROR
                "NONE" -> NONE
                else -> error("不存在的等级 | This is NOT A VALID level!")
            }
        }
    }
}