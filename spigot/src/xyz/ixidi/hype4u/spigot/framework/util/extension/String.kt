package xyz.ixidi.hype4u.spigot.framework.util.extension

import org.bukkit.ChatColor

internal fun String.color() = ChatColor.translateAlternateColorCodes('&', this)