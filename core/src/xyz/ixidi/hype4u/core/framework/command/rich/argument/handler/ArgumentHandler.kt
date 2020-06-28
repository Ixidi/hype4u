package xyz.ixidi.hype4u.core.framework.command.rich.argument.handler

import org.bukkit.command.CommandSender

abstract class ArgumentHandler<T> {

    @Throws(CommandHandlerArgumentException::class)
    abstract fun handle(sender: CommandSender, argumentName: String, string: String): T

    abstract fun tabComplete(sender: CommandSender, argumentName: String, string: String): List<String>

    protected fun argumentError(key: String, vararg parameters: Pair<String, Any?>) =
        CommandHandlerArgumentException(key, *parameters)

}