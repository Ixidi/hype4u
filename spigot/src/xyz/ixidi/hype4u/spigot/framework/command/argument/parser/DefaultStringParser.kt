package xyz.ixidi.hype4u.spigot.framework.command.argument.parser

import org.bukkit.command.CommandSender
import kotlin.reflect.KClass

object DefaultStringParser : ArgumentParser<String> {

    override val valueClass: KClass<String> = String::class

    override fun parse(commandSender: CommandSender, string: String): String? = string

    override fun suggestions(commandSender: CommandSender, start: String): List<String> = emptyList()

}