# JvmOnlyLinter

This gradle/kotlin compiler plugin(KCP) is a linter design for kotlin multiplatform library development
with JVM target and inspect if your kotlin code called the declaration with `@JvmOnly` annotation,
if so, the compiler will get error and cannot be compiled successfully(This can be set via dsl by default
its level is `ERROR`, `WARNING` is also available If set this value to `WARNING` the compiler will not get
error, just print a warning info to the console).

This compiler plugin support K2

# Apply plugin

```kotlin
// First of all add my maven repository both pluginManagement and 

// build.gradle.kts
plugins {
    kotlin("multiplatform") // or kotlin("jvm")
    id("cn.rtast.jvmonly-linter") version "<version>"
}

repositories {
    mavenCentral()
    maven("https://repo.maven.rtast.cn/releases")
}

// You don't need to configure the dependencies by hand, the plugin will automatically 
// configure the runtime (runtime is very small, just one annotation class and one enum class)
// And the runtime support many platform like: mingwX64, linuxX64, linuxArmX64, macosX64, macosArmX64
// js(IR, browser, nodejs), wasmJs(browser, nodejs)


// settings.gradle.kts
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://repo.maven.rtast.cn/releases")
    }
}
```

> The latest version of plugin and dependency can be found
> at https://repo.maven.rtast.cn/#/releases/cn/rtast/jvmonly-linter/jvmonly-linter-gradle-plugin

# Configure plugin

KotlinDSL

```kotlin
jvmOnly {
    // set enabled status by default it is true
    enabled = true
    // set development mode, if true, all the inspection are disabled, by default it is false
    developmentMode = true
    // compiler report level, by default it is ERROR, You can also set it to NONE or WARNING 
    reportLevel = JvmOnlyReportLevel.ERROR
    // set the custom annotation, by default it is @JvmOnly (this property is the annotation class reference like `cn.rtast.jvmonly.linter.JvmOnly`)
    customAnnotation = "com.example.annotations.MyCustomAnnotation"
    // Notice JvmOnlyLinter processing annotations at the IR phase so, make sure your custom
    // annotation retention is `BINARY` or `RUNTIME` (`BINARY` is recommended) 
}
```

# Use in code

```kotlin
@JvmOnly  // or use the annotation yourself
fun test() {

}

fun main() {
    test()
    // if comment this line the compiler will not report any warning or error
    // also if you enable the development mode the compiler will also not report any warning or error 
}
```

# Licenses

- This project is open source under [Apache-2.0](./LICENSE) license, that is:
    - You can directly use the functions provided by this project without any authorization
    - You can distribute, modify and derive the source code at will under the condition of **indicating the source
      copyright information**

# Special thanks

<div>

<img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jetbrains.png" alt="JetBrainsIcon" width="128">

<a href="https://www.jetbrains.com/opensource/"><code>JetBrains Open Source</code></a> provided the powerful IDE support

</div>

# 中文版本

此插件适用于开发Kotlin multiplatform 库时使用, 它提供了一个简单的注解来将一个IrCall标记为仅Jvm可用(或仅推荐JVM使用)。

插件会检查所有的IrCall, 检查是否在Kotlin中调用了被@JvmOnly注解的declaration, 如果检测到了可以让编译器输出错误或者警告。

插件支持K2

# 应用插件

和上面的一样, 需要在build.gradle.kts和settings.gradle.kts中都添加我的maven仓库。

插件的最新版本可以在 https://repo.maven.rtast.cn/#/releases/cn/rtast/jvmonly-linter/jvmonly-linter-gradle-plugin 找到。

# 配置插件

```kotlin
jvmOnly {
    // 设置插件是否启用 默认为true
    enabled = true
    // 是否开启开发模式, 如果开启了之后则不会检查注解, 默认为false
    developmentMode = true
    // 编译器日志报告等级 默认为ERROR
    reportLevel = JvmOnlyReportLevel.ERROR
    // 设置自定义注解, 这是一个注解的引用 默认为 cn.rtast.jvmonly.linter.JvmOnly
    customAnnotation = "com.example.annotations.MyCustomAnnotation"
}
```

# 在代码中使用

```kotlin
@JvmOnly
fun test() {

}

fun main() {
    // 这里在Kotlin中调用了被@JvmOnly注解的函数则会报错, 导致无法编译
    test()
}
```

# 开源

- 本项目以[Apache-2.0](./LICENSE)许可开源, 即:
    - 你可以直接使用该项目提供的功能, 无需任何授权
    - 你可以在**注明来源版权信息**的情况下对源代码进行任意分发和修改以及衍生

# 鸣谢

<div>

<img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jetbrains.png" alt="JetBrainsIcon" width="128">

<a href="https://www.jetbrains.com/opensource/"><code>JetBrains Open Source</code></a> 提供的强大IDE支持

</div>