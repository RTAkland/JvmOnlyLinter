/*
 * Copyright © 2025 RTAkland
 * Date: 15:47
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


package cn.rtast.jvmonly.linter

open class JvmOnlyGradleExtension {
    var enabled: Boolean = true
    var developmentMode: Boolean = false
    var reportLevel: JvmOnlyReportLevel = JvmOnlyReportLevel.ERROR
    var customAnnotation: String = "cn.rtast.jvmonly.linter.JvmOnly"
}