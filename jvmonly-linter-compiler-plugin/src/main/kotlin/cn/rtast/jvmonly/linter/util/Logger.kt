/*
 * Copyright © 2025 RTAkland
 * Date: 16:49
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


package cn.rtast.jvmonly.linter.util

import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector

fun MessageCollector.log(content: Any?) {
    this.report(CompilerMessageSeverity.WARNING, content.toString())
}

fun MessageCollector.error(content: Any?) {
    this.report(CompilerMessageSeverity.ERROR, content.toString())
}