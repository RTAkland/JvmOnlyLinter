/*
 * Copyright © 2025 RTAkland
 * Date: 16:46
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


@file:OptIn(ExperimentalCompilerApi::class)

package cn.rtast.jvmonly.linter

import cn.rtast.jvmonly.linter.util.DEVELOPMENT_MODE
import cn.rtast.jvmonly.linter.util.ENABLED
import com.google.auto.service.AutoService
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CommonConfigurationKeys
import org.jetbrains.kotlin.config.CompilerConfiguration

@AutoService(CompilerPluginRegistrar::class)
class JvmOnlyLinterCompilerRegistrar : CompilerPluginRegistrar() {
    override val supportsK2: Boolean = true

    override fun ExtensionStorage.registerExtensions(
        configuration: CompilerConfiguration
    ) {
        val messageCollector = configuration.get(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY, MessageCollector.NONE)
        if (!configuration.get(ENABLED)?.toString().toBoolean()) return
        if (configuration.get(DEVELOPMENT_MODE)?.toString().toBoolean()) return
        IrGenerationExtension.registerExtension(JvmOnlyLinterExtension(messageCollector, configuration))
    }
}