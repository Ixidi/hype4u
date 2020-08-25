package xyz.ixidi.hype4u.spigot.framework.command.argument

import xyz.ixidi.hype4u.spigot.framework.command.argument.parser.ArgumentParser
import kotlin.reflect.KClass

interface ArgumentParsers {

    fun <T : Any> availableParsers(valueClass: KClass<T>): List<ArgumentParser<T>>
    fun <T : ArgumentParser<*>> parserByClass(parserClass: KClass<T>): T?
    fun <T : Any> defaultParser(valueClass: KClass<T>): ArgumentParser<T>?

}