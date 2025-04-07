/*
 * Copyright © 2025 RTAkland
 * Date: 16:47
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


package cn.rtast.kotlin.java.lint.compiler

import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.name.FqName

class KotlinJavaLintTransformer(
    private val pluginContext: IrPluginContext,
    private val messageCollector: MessageCollector
) : IrElementTransformerVoidWithContext() {
    override fun visitFunctionNew(declaration: IrFunction): IrStatement {
        return declaration.lint(declaration.symbol.toString(), DeclarationType.Function)
    }

    override fun visitClassNew(declaration: IrClass): IrStatement {
        return declaration.lint(declaration.symbol.toString(), DeclarationType.Class)
    }

    override fun visitFieldNew(declaration: IrField): IrStatement {
        return declaration.lint(declaration.symbol.toString(), DeclarationType.Field)
    }

    override fun visitPropertyNew(declaration: IrProperty): IrStatement {
        return declaration.lint(declaration.symbol.toString(), DeclarationType.Property)
    }

    private fun IrDeclaration.lint(name: String, type: DeclarationType): IrDeclaration {
        if (this.hasAnnotation(FqName("cn.rtast.kotlin.java.lint.annotations.JvmOnly"))) {
            messageCollector.report(
                CompilerMessageSeverity.ERROR,
                "你正在使用一个仅在Java中使用的${type.nameZh}: $name | You are trying to use a Java only ${type.nameEn}"
            )
        }
        return this
    }
}