/*
 * Copyright © 2025 RTAkland
 * Date: 2025/4/9
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


package cn.rtast.jvmonly.linter.util

import org.jetbrains.kotlin.name.FqName

val String.fqn get() = FqName(this)