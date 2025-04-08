/*
 * Copyright © 2025 RTAkland
 * Date: 16:47
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


@file:OptIn(UnsafeDuringIrConstructionAPI::class)
@file:Suppress("unused")

package cn.rtast.jvmonly.linter

import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.symbols.UnsafeDuringIrConstructionAPI
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.name.FqName

class JvmOnlyLinterTransformer(
    private val pluginContext: IrPluginContext,
    private val messageCollector: MessageCollector,
    private val configuration: CompilerConfiguration
) : IrElementTransformerVoidWithContext() {

    override fun visitCall(expression: IrCall): IrExpression {
        expression.symbol.owner.lint()
        return expression
    }

    private fun IrDeclaration.lint(): IrDeclaration {
        val rawReportLevel = configuration[REPORT_LEVEL] ?: JvmOnlyReportLevel.ERROR
        val compilerReportLevel = when (rawReportLevel) {
            JvmOnlyReportLevel.WARNING -> CompilerMessageSeverity.WARNING
            JvmOnlyReportLevel.ERROR -> CompilerMessageSeverity.ERROR
            JvmOnlyReportLevel.NONE -> return this
        }
        val annotation = configuration.get(CUSTOM_ANNOTATION) ?: "cn.rtast.jvmonly.linter.JvmOnly"
        if (this.hasAnnotation(FqName(annotation))) {
            messageCollector.report(
                compilerReportLevel,
                "你正在使用一个仅在Java中使用的声明 ${this.symbol.signature} | You are trying to use a Java only declaration ${this.symbol.signature}."
            )
        }
        return this
    }
}