package xyz.ixidi.hype4u.framework.command.registration

import xyz.ixidi.hype4u.framework.command.Command

interface CommandRegistration {

    fun register(command: Command)

}