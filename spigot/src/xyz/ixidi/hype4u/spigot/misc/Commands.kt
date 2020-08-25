package xyz.ixidi.hype4u.spigot.misc

import xyz.ixidi.hype4u.spigot.framework.command.parser.CommandParser
import xyz.ixidi.hype4u.spigot.framework.command.registration.CommandRegistration

class Commands(
    private val commandRegistration: CommandRegistration,
    private val commandParser: CommandParser
) {

    private val commands = ArrayList<Any>()

    fun add(commandContainer: Any) {
        commands.add(commandContainer)
    }

    fun registerAll() {
        commands.map { container -> commandParser.parse(container) }
            .forEach { list ->
                list.forEach { command ->
                    commandRegistration.register(command)
                }
            }
    }

}