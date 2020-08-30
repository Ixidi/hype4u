package xyz.ixidi.hype4u.framework.command.parser

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.framework.command.Command
import xyz.ixidi.hype4u.framework.command.parameter.ArgumentFunctionParameter
import xyz.ixidi.hype4u.framework.command.parameter.CommandSenderFunctionParameter
import xyz.ixidi.hype4u.framework.command.parameter.FunctionParameter
import xyz.ixidi.hype4u.framework.util.extension.message
import xyz.ixidi.hype4u.framework.FrameworkTranslatableKey
import java.lang.StringBuilder
import kotlin.reflect.KFunction

internal class FunctionCommand(
    override val name: String,
    override val description: String,
    override val aliases: List<String>,
    override val permission: String,
    private val container: Any,
    private val function: KFunction<*>,
    private val functionParameters: List<FunctionParameter>
) : Command {

    private val arguments = functionParameters.filterIsInstance<ArgumentFunctionParameter>().map { it.argument }

    override fun canExecute(commandSender: CommandSender): Boolean = commandSender.hasPermission(permission)

    override fun execute(commandSender: CommandSender, args: List<String>) {
        try {
            val finalParameters = ArrayList<Any?>()
            finalParameters.add(container)

            var argIndex = 0
            f@ for (parameter in functionParameters) {
                when (parameter) {
                    is CommandSenderFunctionParameter -> {
                        if (!parameter.matches(commandSender)) {
                            commandSender.message(FrameworkTranslatableKey.MESSAGE_COMMAND_SENDER_LEVEL)
                            return
                        }

                        finalParameters.add(commandSender)
                    }
                    is ArgumentFunctionParameter -> {
                        val argI = argIndex++
                        val argument = parameter.argument

                        if (args.size - 1 < argI) {
                            val def = argument.default
                            if (def != null) {
                                finalParameters.add(argument.parser.parse(commandSender, def) ?: return)
                                continue@f
                            }

                            if (argument.isNullable) {
                                finalParameters.add(null)
                                continue@f
                            }

                            val error = StringBuilder("&7$name ${args.joinToString(" ")} &4&l???")
                            commandSender.message(
                                FrameworkTranslatableKey.MESSAGE_COMMAND_MISSING_PARAMETER,
                                "error" to error,
                                "name" to argument.name,
                                "desc" to argument.description
                            )
                            return
                        }

                        if (argument.isGreedyString) {
                            val list = ArrayList<String>()
                            for (i in argI until args.size) {
                                list.add(argument.parser.parse(commandSender, args[i]) as? String ?: return)
                            }
                            finalParameters.add(list.joinToString(" "))
                            continue@f
                        }

                        finalParameters.add(argument.parser.parse(commandSender, args[argI]) ?: return)
                    }
                }
            }

            function.call(*finalParameters.toArray())
        } catch (exception: Exception) {
            exception.printStackTrace()
            commandSender.message(
                FrameworkTranslatableKey.MESSAGE_COMMAND_UNKNOWN_ERROR,
                "error" to if (commandSender.isOp) exception.message else "Prosimy o powiadomienie administracji."
            )
        }
    }

    override fun tabComplete(commandSender: CommandSender, args: List<String>): List<String> {
        if (args.size > arguments.size && !arguments.last().isGreedyString) return emptyList()

        val index = (if (args.size >= arguments.size) arguments.size else args.size) - 1

        return arguments[index].parser.suggestions(commandSender, args.last())
    }

}