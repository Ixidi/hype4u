package xyz.ixidi.hype4u.framework.command.annotation

import xyz.ixidi.hype4u.framework.command.argument.parser.ArgumentParser
import kotlin.reflect.KClass

@Retention
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class ArgParser(
    val clazz: KClass<ArgumentParser<*>>
)