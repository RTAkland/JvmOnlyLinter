/*
 * Copyright © 2025 RTAkland
 * Date: 16:53
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


package cn.rtast.jvmonly.linter.enums

enum class DeclarationType(val nameZh: String, val nameEn: String) {
    Class("类", "Class"),
    Function("方法", "Function"),
    Property("属性", "Property"),
    Field("字段", "Field")
}