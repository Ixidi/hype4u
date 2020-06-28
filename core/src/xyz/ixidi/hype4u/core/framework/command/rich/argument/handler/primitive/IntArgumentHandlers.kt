package xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.primitive

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandler
import xyz.ixidi.hype4u.core.permission.CoreLangKeys

class IntArgumentHandler : ArgumentHandler<Int>() {

    override fun handle(sender: CommandSender, argumentName: String, string: String): Int =
        string.toIntOrNull() ?: throw argumentError(CoreLangKeys.ERROR_INTEGER_COMMAND_MESSAGE)

    override fun tabComplete(sender: CommandSender, argumentName: String, string: String): List<String> = emptyList()

}

class PositiveIntArgumentHandler : ArgumentHandler<Int>() {

    override fun handle(sender: CommandSender, argumentName: String, string: String): Int {
        val int = string.toIntOrNull()
        if (int == null || int <= 0) throw argumentError(CoreLangKeys.ERROR_POSITIVE_INTEGER_COMMAND_MESSAGE)
        return int
    }

    override fun tabComplete(sender: CommandSender, argumentName: String, string: String): List<String> = emptyList()

}



