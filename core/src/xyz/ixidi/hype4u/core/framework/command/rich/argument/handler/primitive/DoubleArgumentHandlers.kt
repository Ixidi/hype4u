package xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.primitive

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandler
import xyz.ixidi.hype4u.core.permission.CoreLangKeys

class DoubleArgumentHandler : ArgumentHandler<Double>() {

    override fun handle(sender: CommandSender, argumentName: String, string: String): Double =
        string.toDoubleOrNull() ?: throw argumentError(CoreLangKeys.ERROR_DOUBLE_COMMAND_MESSAGE)

    override fun tabComplete(sender: CommandSender, argumentName: String, string: String): List<String> = emptyList()

}

class PositiveDoubleArgumentHandler : ArgumentHandler<Double>() {

    override fun handle(sender: CommandSender, argumentName: String, string: String): Double {
        val double = string.toDoubleOrNull()
        if (double == null || double <= 0) throw argumentError(CoreLangKeys.ERROR_POSITIVE_DOUBLE_COMMAND_MESSAGE)
        return double
    }

    override fun tabComplete(sender: CommandSender, argumentName: String, string: String): List<String> = emptyList()

}