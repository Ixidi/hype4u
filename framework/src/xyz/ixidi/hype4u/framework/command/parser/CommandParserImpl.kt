package xyz.ixidi.hype4u.framework.command.parser

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.framework.command.Command
import xyz.ixidi.hype4u.framework.command.SubCommandsCommand
import xyz.ixidi.hype4u.framework.command.annotation.*
import xyz.ixidi.hype4u.framework.command.parameter.CommandSenderFunctionParameter
import xyz.ixidi.hype4u.framework.command.parameter.FunctionParameter
import xyz.ixidi.hype4u.framework.command.argument.Argument
import xyz.ixidi.hype4u.framework.command.argument.ArgumentParsers
import xyz.ixidi.hype4u.framework.command.argument.parser.ArgumentParser
import xyz.ixidi.hype4u.framework.command.parameter.ArgumentFunctionParameter
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.jvmErasure

private typealias CommandAnnotation = xyz.ixidi.hype4u.framework.command.annotation.Command

internal class CommandParserImpl(
    private val argumentParsers: ArgumentParsers
) : CommandParser {

    class ContainerAnnotationMissingException(val kClass: KClass<*>) :
        Exception("Container annotation of class ${kClass.simpleName} is missing.")

    class ContainerAnnotationRepeatingException(val kClass: KClass<*>) :
        Exception("Class ${kClass.simpleName} has two container annotations, only one is allowed.")

    class SenderAnnotationMismatchException :
        Exception("Only CommandSender implementations can be annotated with @Sender.")

    class DefaultParserNotProvidedException(val clazz: KClass<*>) :
        Exception("There is not default argument parser provided for ${clazz.simpleName} type.")

    class ParserNotInitializedException(val parserClass: KClass<ArgumentParser<*>>) :
        Exception("Argument parser ${parserClass.simpleName} is not initialized.")

    class ParserTypeMismatchException(val parserClass: KClass<out ArgumentParser<*>>, val typeClass: KClass<*>) :
        Exception("Argument parser ${parserClass.simpleName} cannot be applied to ${typeClass.simpleName} type.")

    class CommandArgumentException(message: String) : Exception(message)


    override fun parse(commandContainer: Any): List<Command> {
        val clazz = commandContainer::class
        val singleCommandContainerAnnotation = clazz.findAnnotation<SingleCommandsContainer>()
        val subCommandContainerAnnotation = clazz.findAnnotation<SubCommandsContainer>()
        if (singleCommandContainerAnnotation == null && subCommandContainerAnnotation == null) {
            throw ContainerAnnotationMissingException(clazz)
        }

        if (singleCommandContainerAnnotation != null && subCommandContainerAnnotation != null) {
            throw ContainerAnnotationRepeatingException(clazz)
        }

        val commandList = ArrayList<Command>()
        for (function in clazz.declaredFunctions) {
            if (!function.isAccessible) {
                function.isAccessible = true
            }
            if (function.findAnnotation<CommandAnnotation>() == null) {
                continue
            }

            commandList.add(parseSingleCommand(commandContainer, function))
        }

        if (subCommandContainerAnnotation != null) {
            return subCommandContainerAnnotation.command.run {
                listOf(
                    SubCommandsCommand(
                        name = name,
                        description = desc,
                        aliases = aliases.toList(),
                        permission = permission,
                        subCommands = commandList
                    )
                )
            }
        }

        return commandList
    }

    private fun parseSingleCommand(container: Any, function: KFunction<*>): Command {
        val commandAnnotation = function.findAnnotation<CommandAnnotation>() ?: throw Exception()
        val parameters = commandAnnotation.run {
            ArrayList<FunctionParameter>().also { parameters ->
                for (parameter in function.parameters) {
                    val clazz = parameter.type.jvmErasure
                    if (parameter.findAnnotation<Sender>() != null) {
                        if (!clazz.isSubclassOf(CommandSender::class)) {
                            throw SenderAnnotationMismatchException()
                        }

                        parameters.add(CommandSenderFunctionParameter(clazz as KClass<out CommandSender>))
                        continue
                    }

                    val argAnnotation = parameter.findAnnotation<Arg>()
                    if (argAnnotation != null) {
                        val argParserAnnotation = parameter.findAnnotation<ArgParser>()
                        val parser = if (argParserAnnotation != null) {
                            argumentParsers.parserByClass(argParserAnnotation.clazz)
                                ?: throw ParserNotInitializedException(
                                    argParserAnnotation.clazz
                                )
                        } else {
                            argumentParsers.defaultParser(clazz) ?: throw DefaultParserNotProvidedException(clazz)
                        }

                        if (!clazz.isSubclassOf(parser.valueClass)) {
                            throw ParserTypeMismatchException(
                                parser::class,
                                clazz
                            )
                        }

                        val greedyString = if (parameter.findAnnotation<GreedyString>() != null) {
                            if (!clazz.isSubclassOf(String::class)) {
                                throw CommandArgumentException("Only String can be annotated with @GreedyString.")
                            }
                            true
                        } else {
                            false
                        }

                        val argument = Argument(
                            name = argAnnotation.name,
                            description = argAnnotation.desc,
                            default = if (argAnnotation.def.isNotEmpty()) argAnnotation.def else null,
                            isGreedyString = greedyString,
                            isNullable = parameter.type.isMarkedNullable,
                            parser = parser
                        )

                        parameters.add(ArgumentFunctionParameter(argument))
                    }
                }
            }
        }

        val arguments = parameters.filterIsInstance<ArgumentFunctionParameter>().map { it.argument }
        for ((i, arg) in arguments.withIndex()) {
            if (i != arguments.size - 1) {
                if (arg.isGreedyString) {
                    throw CommandArgumentException(
                        "Only last argument can be greedy string."
                    )
                }
                if (arg.isNullable) {
                    throw CommandArgumentException(
                        "Only last argument can be optional (nullable)."
                    )
                }
            }
        }

        return commandAnnotation.run {
            FunctionCommand(
                name = name,
                description = desc,
                aliases = aliases.toList(),
                permission = permission,
                container = container,
                function = function,
                functionParameters = parameters
            )
        }
    }

}