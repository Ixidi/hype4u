package xyz.ixidi.hype4u.framework.command

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.framework.language.TranslatableKey
import xyz.ixidi.hype4u.framework.util.extension.message
import xyz.ixidi.hype4u.misc.FrameworkTranslatableKey

internal class SubCommandsCommand(
    override val name: String,
    override val description: String,
    override val aliases: List<String>,
    override val permission: String,
    private val subCommands: List<Command>
) : Command {

    override fun canExecute(commandSender: CommandSender): Boolean = commandSender.hasPermission(permission)

    override fun execute(commandSender: CommandSender, args: List<String>) {
        if (args.isEmpty()) {
            commandSender.sendMessage("help") //TODO
            return
        }

        val command = subCommands.firstOrNull { it.name.equals(args[0], true) && it.canExecute(commandSender) }
        if (command == null) {
            commandSender.message(FrameworkTranslatableKey.MESSAGE_COMMAND_NO_PERMISSION)
            return
        }

        val arguments = if (args.size == 1) emptyList() else args.slice(1 until args.size)
        command.execute(commandSender, arguments)
    }

    override fun tabComplete(commandSender: CommandSender, args: List<String>): List<String> =
        subCommands.filter { it.name.startsWith(args.last(), true) && it.canExecute(commandSender) }.map { it.name }

}