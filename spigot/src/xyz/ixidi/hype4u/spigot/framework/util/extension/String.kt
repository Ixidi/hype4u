package xyz.ixidi.hype4u.spigot.framework.util.extension

import net.md_5.bungee.api.ChatColor
import xyz.ixidi.hype4u.spigot.framework.util.ColorsFormatter

fun String.color() = ColorsFormatter.formatColors(this)