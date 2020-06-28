package xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.primitive

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandler

class StringArgumentHandler : ArgumentHandler<String>() {

    override fun handle(sender: CommandSender, argumentName: String, string: String): String = string

    override fun tabComplete(sender: CommandSender, argumentName: String, string: String): List<String> = emptyList()

}