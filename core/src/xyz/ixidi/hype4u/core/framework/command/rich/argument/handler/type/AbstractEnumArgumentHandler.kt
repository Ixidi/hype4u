package xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.type

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandler
import xyz.ixidi.hype4u.core.framework.util.camelCaseName
import kotlin.reflect.KClass

abstract class AbstractEnumArgumentHandler<T : Enum<*>>(
    enumClass: KClass<T>
) : ArgumentHandler<T>() {

    private val constants = enumClass.java.enumConstants
    private val constantsString = constants.joinToString(", ") { it.camelCaseName() }

    final override fun handle(sender: CommandSender, argumentName: String, string: String): T =
        constants.firstOrNull { it.camelCaseName().equals(string, true) }
            ?: throw argumentError(
                "core.command.enumHandlerValues",
                "argument" to argumentName,
                "values" to constantsString
            )

    final override fun tabComplete(sender: CommandSender, argumentName: String, string: String): List<String> =
        constants.filter { it.name.startsWith(string, true) }.map { it.name.toLowerCase() }

}