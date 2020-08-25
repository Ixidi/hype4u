package xyz.ixidi.hype4u.spigot.framework.command.parser

import xyz.ixidi.hype4u.spigot.framework.command.Command
import kotlin.reflect.KClass

interface CommandParser {

    fun parse(commandContainer: Any): List<Command>

}