package xyz.ixidi.hype4u.core.framework.command.rich.argument

import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandler
import kotlin.reflect.KClass

@Retention
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Argument(
    val handlerClass: KClass<out ArgumentHandler<*>>,
    val name: String = ""
)