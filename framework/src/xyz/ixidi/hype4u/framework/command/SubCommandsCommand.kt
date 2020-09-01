package xyz.ixidi.hype4u.framework.command

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.framework.util.extension.message
import xyz.ixidi.hype4u.framework.FrameworkTranslatableKey

internal class SubCommandsCommand(
    override val name: String,
    override val description: String,
    override val aliases: List<String>,
    private val subCommands: List<Command>
) : Command {

    override val permission: String = ""

    override fun canExecute(commandSender: CommandSender): Boolean = commandSender.hasPermission(permission)

    override fun execute(commandSender: CommandSender, args: List<String>) {
        if (subCommands.none { it.canExecute(commandSender) }) {
            commandSender.message(FrameworkTranslatableKey.MESSAGE_COMMAND_NO_PERMISSION)
            return
        }

        if (args.isEmpty()) {
            val available = subCommands.filter { it.canExecute(commandSender) }
            commandSender.message(FrameworkTranslatableKey.MESSAGE_COMMAND_HELP_HEADER)
            available.forEach {
                commandSender.message(
                    FrameworkTranslatableKey.MESSAGE_COMMAND_HELP_ENTRY,
                    "command" to "/$name ${it.name}",
                    "desc" to it.description
                )
            }
            return
        }

        val command = subCommands.firstOrNull { it.name.equals(args[0], true) && it.canExecute(commandSender) }
        if (command == null) {
            commandSender.message(FrameworkTranslatableKey.MESSAGE_COMMAND_SUBCOMMAND_NOT_EXIST, "command" to "/$name")
            return
        }

        if (!command.canExecute(commandSender)) {
            commandSender.message(FrameworkTranslatableKey.MESSAGE_COMMAND_NO_PERMISSION)
            return
        }

        val arguments = if (args.size == 1) emptyList() else args.slice(1 until args.size)
        command.execute(commandSender, arguments)
    }

    override fun tabComplete(commandSender: CommandSender, args: List<String>): List<String> =
        subCommands.filter {
            (it.name.startsWith(args.last(), true) || it.aliases.any { s ->
                s.startsWith(
                    args.last(),
                    true
                )
            }) && it.canExecute(commandSender)
        }.map { it.name }

}