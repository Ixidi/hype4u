package xyz.ixidi.hype4u.spigot.framework.command

import org.bukkit.command.CommandSender

interface Command {

    val name: String
    val description: String
    val aliases: List<String>
    val permission: String

    fun canExecute(commandSender: CommandSender): Boolean

    fun execute(commandSender: CommandSender, args: List<String>)

    fun tabComplete(commandSender: CommandSender, args: List<String>): List<String>

}