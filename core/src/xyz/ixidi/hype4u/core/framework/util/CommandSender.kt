package xyz.ixidi.hype4u.core.framework.util

import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import org.koin.core.get
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import xyz.ixidi.hype4u.core.internal.injection.DependencyInjection

fun CommandSender.message(key: String, vararg parameters: Pair<String, Any?>) {
    sendMessage(languageString(key, *parameters).color())
}