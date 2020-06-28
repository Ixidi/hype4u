package xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.type

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandler
import xyz.ixidi.hype4u.core.permission.CoreLangKeys

class PlayerArgumentHandler : ArgumentHandler<Player>() {

    override fun handle(sender: CommandSender, argumentName: String, string: String): Player
            = Bukkit.getPlayer(string) ?: throw argumentError(CoreLangKeys.PLAYER_OFFLINE_MESSAGE, "name" to string)

    override fun tabComplete(sender: CommandSender, argumentName: String, string: String): List<String>
            = Bukkit.getOnlinePlayers().filter { it.name.startsWith(string, true) }.map { it.name }

}