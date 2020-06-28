package xyz.ixidi.hype4u.core.framework.util

import org.bukkit.ChatColor

fun String.color() = ChatColor.translateAlternateColorCodes('&', this)
