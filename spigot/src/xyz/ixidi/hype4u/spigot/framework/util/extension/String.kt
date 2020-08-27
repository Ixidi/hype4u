package xyz.ixidi.hype4u.spigot.framework.util.extension

import net.md_5.bungee.api.ChatColor

fun String.color(): String {
    var format = ChatColor.translateAlternateColorCodes('&', this)
    //rgb &#kolor

    val c = ChatColor.COLOR_CHAR
    val regex = Regex("&#([a-f0-9]{6})")
    format = regex.replace(format) {
        val hex = it.groupValues[1].toCharArray().joinToString("") { x -> "$c$x"}
        "${c}x$hex"
    }

    return format
}