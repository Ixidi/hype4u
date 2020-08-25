package xyz.ixidi.hype4u.spigot.framework.command.argument.parser

import org.bukkit.Server
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.spigot.misc.TranslatableKey
import xyz.ixidi.hype4u.spigot.framework.util.extension.message
import kotlin.reflect.KClass

class DefaultPlayerParser(
    private val server: Server
) : ArgumentParser<Player> {

    override val valueClass: KClass<Player> = Player::class

    override fun parse(commandSender: CommandSender, string: String): Player? {
        val player = server.getPlayer(string)
        if (player == null) {
            commandSender.message(TranslatableKey.MESSAGE_PLAYER_OFFLINE, "name" to string)
            return null
        }

        return player
    }

    override fun suggestions(commandSender: CommandSender, start: String): List<String> = server.onlinePlayers.filter { it.name.startsWith(start, true) }.map { it.name }

}