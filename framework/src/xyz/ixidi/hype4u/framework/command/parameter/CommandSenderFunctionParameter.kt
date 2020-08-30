package xyz.ixidi.hype4u.framework.command.parameter

import org.bukkit.command.CommandSender
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

internal class CommandSenderFunctionParameter(
    private val senderClass: KClass<out CommandSender>
) : FunctionParameter {

   fun matches(value: CommandSender): Boolean = value::class.isSubclassOf(senderClass)

}