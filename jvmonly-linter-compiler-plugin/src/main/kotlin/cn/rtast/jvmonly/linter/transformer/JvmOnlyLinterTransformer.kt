/*
 * Copyright © 2025 RTAkland
 * Date: 16:47
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */

@file:OptIn(UnsafeDuringIrConstructionAPI::class, ObsoleteDescriptorBasedAPI::class)

package cn.rtast.jvmonly.linter.transformer

import cn.rtast.jvmonly.linter.JvmOnlyReportLevel
import cn.rtast.jvmonly.linter.util.CUSTOM_ANNOTATION
import cn.rtast.jvmonly.linter.util.REPORT_LEVEL
import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.symbols.UnsafeDuringIrConstructionAPI
import org.jetbrains.kotlin.ir.util.file
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.parentAsClass

class JvmOnlyLinterTransformer(
    @Suppress("unused")
    private val pluginContext: IrPluginContext,
    private val messageCollector: MessageCollector,
    private val configuration: CompilerConfiguration
) : IrElementTransformerVoidWithContext() {
    override fun visitCall(expression: IrCall): IrExpression {
        val rawReportLevel = configuration[REPORT_LEVEL] ?: JvmOnlyReportLevel.ERROR
        val compilerReportLevel = when (rawReportLevel) {
            JvmOnlyReportLevel.WARNING -> CompilerMessageSeverity.WARNING
            JvmOnlyReportLevel.ERROR -> CompilerMessageSeverity.ERROR
            JvmOnlyReportLevel.NONE -> return super.visitCall(expression)
        }
        val annotation = configuration.get(CUSTOM_ANNOTATION) ?: "cn.rtast.jvmonly.linter.JvmOnly"
        val hasAnnotation =
            expression.symbol.owner.annotations.any { it.symbol.owner.parentAsClass.fqNameWhenAvailable?.asString() == annotation }
        if (hasAnnotation) {
            val name = expression.symbol.owner.name.toString()
            val className = expression.symbol.owner.parentAsClass.name.toString()
            expression.symbol.owner.file
            messageCollector.report(
                compilerReportLevel,
                "You are trying to use a Java only declaration $className:$name | " +
                        "你正在使用一个仅在Java中使用的声明 $className:$name"
            )
        }
        return super.visitCall(expression)
    }
}