package xyz.ixidi.hype4u.framework.command.argument

import jdk.nashorn.internal.parser.DateParser
import org.bukkit.Server
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.framework.command.argument.parser.ArgumentParser
import xyz.ixidi.hype4u.framework.command.argument.parser.DefaultDateParser
import xyz.ixidi.hype4u.framework.command.argument.parser.DefaultPlayerParser
import xyz.ixidi.hype4u.framework.command.argument.parser.DefaultStringParser
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

internal class ArgumentParsersImpl(
    server: Server
) : ArgumentParsers {

    private class ParserEntry<T : Any>(val valueClass: KClass<T>, val parser: ArgumentParser<T>, val isDefault: Boolean)

    private val availableParsers = ArrayList<ParserEntry<*>>()

    private fun <T : Any> default(valueClass: KClass<T>, parser: ArgumentParser<T>) {
        availableParsers.add(ParserEntry(valueClass, parser, true))
    }

    init {
        default(String::class, DefaultStringParser)
        default(Player::class, DefaultPlayerParser(server))
        default(Date::class, DefaultDateParser)
    }

    override fun <T : Any> availableParsers(valueClass: KClass<T>): List<ArgumentParser<T>> =
        availableParsers.filter { it.valueClass.isSubclassOf(valueClass) }.map { it.parser } as List<ArgumentParser<T>>

    override fun <T : ArgumentParser<*>> parserByClass(parserClass: KClass<T>): T? = availableParsers.firstOrNull { it.valueClass.isSubclassOf(it.parser::class) }?.parser as T?

    override fun <T : Any> defaultParser(valueClass: KClass<T>): ArgumentParser<T>? = availableParsers.firstOrNull { it.valueClass.isSubclassOf(valueClass) && it.isDefault }?.parser as ArgumentParser<T>?

}