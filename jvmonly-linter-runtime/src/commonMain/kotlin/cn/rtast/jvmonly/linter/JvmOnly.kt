/*
 * Copyright © 2025 RTAkland
 * Date: 16:45
 * Open Source Under Apache-2.0 License
 * https://www.apache.org/licenses/LICENSE-2.0
 */


package cn.rtast.jvmonly.linter

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.FIELD,
    AnnotationTarget.ANNOTATION_CLASS
)
@Retention(AnnotationRetention.RUNTIME)
@RequiresOptIn(
    message = "此API建议/只能在Java中使用 | This API can only / recommend be used in Java.",
    level = RequiresOptIn.Level.ERROR
)
annotation class JvmOnly