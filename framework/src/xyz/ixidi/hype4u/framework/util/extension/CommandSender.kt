package xyz.ixidi.hype4u.framework.util.extension

import org.bukkit.command.CommandSender
import org.koin.core.get
import xyz.ixidi.hype4u.framework.language.TranslatableKey
import xyz.ixidi.hype4u.framework.message.Messages
import xyz.ixidi.hype4u.framework.injection.FrameworkDependencyInjection

fun CommandSender.message(key: TranslatableKey, vararg parameters: Pair<String, Any?> = emptyArray()) {
    val message = FrameworkDependencyInjection.get<Messages>().getColoredMessage(key, *parameters)
    sendMessage(message)
}