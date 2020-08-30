package xyz.ixidi.hype4u.framework.command.registration

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.framework.language.TranslatableKey
import xyz.ixidi.hype4u.framework.command.Command
import xyz.ixidi.hype4u.framework.util.extension.message
import xyz.ixidi.hype4u.misc.FrameworkTranslatableKey

private typealias BukkitCommand = org.bukkit.command.Command

internal class CommandPerformer(
    private val command: Command
) : BukkitCommand(
    command.name, command.description, "", command.aliases
) {

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<String>): Boolean {
        if (!sender.hasPermission(command.permission)) {
            sender.message(FrameworkTranslatableKey.MESSAGE_COMMAND_NO_PERMISSION)
            return true
        }

        command.execute(sender, args.toList())
        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>): MutableList<String> =
        command.tabComplete(sender, args.toList()).toMutableList()

}