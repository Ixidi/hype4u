package xyz.ixidi.hype4u.core.framework.command

import org.bukkit.command.CommandSender

interface Command {

    fun onExecute(sender: CommandSender, name: String, args: List<String>)
    fun onTabComplete(sender: CommandSender, args: List<String>): List<String>

}

internal fun command(
    onExecute: (sender: CommandSender, args: List<String>) -> Unit,
    onTabComplete: (sender: CommandSender, args: List<String>) -> List<String> = { _, _ -> emptyList() }
) = object : Command {

    override fun onExecute(sender: CommandSender, name: String, args: List<String>) { onExecute(sender, args) }
    override fun onTabComplete(sender: CommandSender, args: List<String>): List<String> = onTabComplete(sender, args)

}

