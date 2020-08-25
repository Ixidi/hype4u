package xyz.ixidi.hype4u.spigot.framework.command.argument.parser

import org.bukkit.command.CommandSender
import kotlin.reflect.KClass

interface ArgumentParser<T : Any> {

    val valueClass: KClass<T>

    fun parse(commandSender: CommandSender, string: String): T?
    fun suggestions(commandSender: CommandSender, start: String): List<String>

}