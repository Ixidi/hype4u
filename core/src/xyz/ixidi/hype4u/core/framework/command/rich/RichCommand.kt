package xyz.ixidi.hype4u.core.framework.command.rich

import org.bukkit.command.CommandSender
import org.koin.core.get
import xyz.ixidi.hype4u.core.framework.command.Command
import xyz.ixidi.hype4u.core.framework.command.rich.argument.Argument
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandlerManager
import xyz.ixidi.hype4u.core.framework.command.rich.argument.ArgumentInfo
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.CommandHandlerArgumentException
import xyz.ixidi.hype4u.core.internal.injection.DependencyInjection
import xyz.ixidi.hype4u.core.framework.util.languageString
import xyz.ixidi.hype4u.core.framework.util.message
import xyz.ixidi.hype4u.core.permission.CoreLangKeys
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.jvmErasure

internal typealias ArgExc = RichCommand.RichCommandArgumentModelException

class RichCommand<ARGUMENTS_MODEL : Any, SENDER : CommandSender>(
    argumentModelClass: KClass<ARGUMENTS_MODEL>,
    private val executorClass: KClass<SENDER>,
    private val onExecute: ARGUMENTS_MODEL.(sender: SENDER) -> Unit
) : Command {

    class RichCommandArgumentModelException(message: String) : Exception(message)

    private val constructor = argumentModelClass.primaryConstructor!!
    private val argumentInfoList = ArrayList<ArgumentInfo>()
    private val argsUsage by lazy {
        argumentInfoList.joinToString(" ") {
            var string = it.name
            if (it.isVararg) {
                string = "$string..."
            }
            if (it.isNullable) {
                "[<$string>]"
            } else {
                "<$string>"
            }
        }
    }

    init {
        if (!argumentModelClass.isData) throw ArgExc("Argument model must be data class.")

        if (!constructor.isAccessible) constructor.isAccessible = true

        val lastIndex = constructor.parameters.size - 1
        constructor.parameters.withIndex().forEach { (index, parameter) ->
            val argumentAnnotation = parameter.findAnnotation<Argument>()
                ?: throw ArgExc("Parameters ${parameter.name} is not annotated with @Argument.")

            val isVararg = parameter.isVararg
            val isNullable = parameter.type.isMarkedNullable
            if (lastIndex != index) {
                if (isNullable) throw ArgExc("Only last parameter can be nullable, ${parameter.name} is not.")
                if (isVararg) throw ArgExc("Only las parameter can be vararg, ${parameter.name} is not.")
            }

            val name = if (argumentAnnotation.name.isNotBlank()) argumentAnnotation.name else parameter.name
                ?: parameter.type.jvmErasure.simpleName!!
            val handlerClass = argumentAnnotation.handlerClass

            val handler = DependencyInjection.get<ArgumentHandlerManager>()
                .getRegisteredHandler(parameter.type.jvmErasure, handlerClass)
                ?: throw ArgExc("Handler ${handlerClass.simpleName} is not registered.")
            argumentInfoList.add(ArgumentInfo(name, handler, isVararg, isNullable))
        }
    }

    override fun onExecute(sender: CommandSender, name: String, args: List<String>) {
        if (!sender::class.isSubclassOf(executorClass)) {
            sender.message(CoreLangKeys.CANNOT_EXECUTE_COMMAND_MESSAGE)
            return
        }

        if (argumentInfoList.size - 1 >= args.size || (args.size > argumentInfoList.size && !argumentInfoList.last().isVararg)) {
            sender.message(CoreLangKeys.USAGE_COMMAND_MESSAGE, "args" to argsUsage)
            return
        }

        val parameters = ArrayList<Any?>()
        for (i in args.indices) {
            try {
                val info = argumentInfoList[i]

                if (i != args.size - 1) {
                    parameters.add(info.handler.handle(sender, info.name, args[i]))
                } else {
                    if (args.size < argumentInfoList.size) {
                        if (info.isNullable) {
                            parameters.add(null)
                            continue
                        }

                        sender.message(CoreLangKeys.USAGE_COMMAND_MESSAGE, "args" to argsUsage)
                        return
                    }

                    for (j in i until args.size) {
                        parameters.add(info.handler.handle(sender, info.name, args[j]))
                    }
                }
            } catch (ex: CommandHandlerArgumentException) {
                val arguments = ArrayList<String>()
                for (index in args.indices) {
                    val arg = args[index]
                    if (index != i) {
                        arguments.add("&7$arg")
                    } else {
                        arguments.add("&4&n&l$arg&4&l <--")
                    }
                }

                val execution = "&7/$name ${arguments.joinToString(" ")}"
                val errorMessage = languageString(ex.errorMessageKey, *ex.errorMessageParameters)

                sender.message(CoreLangKeys.ARGUMENT_HANDLER_ERROR_MESSAGE, "execution" to execution, "error" to errorMessage)
                return
            }
        }

        val argumentModel = constructor.call(*parameters.toArray())
        argumentModel.run { this.onExecute(sender as SENDER) }
    }

    override fun onTabComplete(sender: CommandSender, args: List<String>): List<String> {
        if (args.isEmpty()) return emptyList()

        val last = argumentInfoList.last()
        if (args.size > argumentInfoList.size) {
            if (!last.isVararg) return emptyList()

            return last.handler.tabComplete(sender, last.name, args.last())
        }

        return last.handler.tabComplete(sender, last.name, args.last())
    }

}
