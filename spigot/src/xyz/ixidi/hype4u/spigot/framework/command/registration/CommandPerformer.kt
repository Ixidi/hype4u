package xyz.ixidi.hype4u.spigot.framework.command.registration

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.spigot.misc.TranslatableKey
import xyz.ixidi.hype4u.spigot.framework.command.Command
import xyz.ixidi.hype4u.spigot.framework.util.extension.message

private typealias BukkitCommand = org.bukkit.command.Command

class CommandPerformer(
    private val command: Command
) : BukkitCommand(
    command.name, command.description, "", command.aliases
) {

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<String>): Boolean {
        if (!sender.hasPermission(command.permission)) {
            sender.message(TranslatableKey.MESSAGE_COMMAND_NO_PERMISSION)
            return true
        }

        command.execute(sender, args.toList())
        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>): MutableList<String> =
        command.tabComplete(sender, args.toList()).toMutableList()

}