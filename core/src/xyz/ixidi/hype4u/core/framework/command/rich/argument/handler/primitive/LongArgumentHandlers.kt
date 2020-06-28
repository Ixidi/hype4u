package xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.primitive

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandler
import xyz.ixidi.hype4u.core.permission.CoreLangKeys

class LongArgumentHandler : ArgumentHandler<Long>() {

    override fun handle(sender: CommandSender, argumentName: String, string: String): Long =
        string.toLongOrNull() ?: throw argumentError(CoreLangKeys.ERROR_INTEGER_COMMAND_MESSAGE)

    override fun tabComplete(sender: CommandSender, argumentName: String, string: String): List<String> = emptyList()

}

class PositiveLongArgumentHandler : ArgumentHandler<Long>() {

    override fun handle(sender: CommandSender, argumentName: String, string: String): Long {
        val long = string.toLongOrNull()
        if (long == null || long <= 0) throw argumentError(CoreLangKeys.ERROR_POSITIVE_INTEGER_COMMAND_MESSAGE)
        return long
    }

    override fun tabComplete(sender: CommandSender, argumentName: String, string: String): List<String> = emptyList()

}