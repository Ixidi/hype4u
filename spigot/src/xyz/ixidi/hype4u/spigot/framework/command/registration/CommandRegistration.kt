package xyz.ixidi.hype4u.spigot.framework.command.registration

import xyz.ixidi.hype4u.spigot.framework.command.Command

interface CommandRegistration {

    fun register(command: Command)

}