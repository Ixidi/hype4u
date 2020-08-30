package xyz.ixidi.hype4u.framework.command.parser

import xyz.ixidi.hype4u.framework.command.Command
import kotlin.reflect.KClass

interface CommandParser {

    fun parse(commandContainer: Any): List<Command>

}