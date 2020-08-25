package xyz.ixidi.hype4u.spigot.framework.util.extension

import org.bukkit.command.CommandSender
import org.koin.core.get
import xyz.ixidi.hype4u.spigot.misc.TranslatableKey
import xyz.ixidi.hype4u.spigot.framework.message.Messages
import xyz.ixidi.hype4u.spigot.injection.DependencyInjection

fun CommandSender.message(key: TranslatableKey, vararg parameters: Pair<String, Any?> = emptyArray()) {
    val message = DependencyInjection.get<Messages>().getColoredMessage(key, *parameters)
    sendMessage(message)
}