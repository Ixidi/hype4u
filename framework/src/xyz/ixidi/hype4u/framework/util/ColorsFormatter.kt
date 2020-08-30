package xyz.ixidi.hype4u.framework.util

import net.md_5.bungee.api.ChatColor
import java.awt.Color

object ColorsFormatter {

    private const val C = ChatColor.COLOR_CHAR

    fun formatColors(text: String): String {
        var format = ChatColor.translateAlternateColorCodes('&', text)

        val hexRegex = Regex("&#([a-f0-9]{6})")
        val rgbRegex = Regex("&\\^\\(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]),([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]),([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\)")

        format = hexRegex.replace(format) { colorString(it.groupValues[1]) }
        format = rgbRegex.replace(format) {
            val t = it.groupValues.subList(1, 4).map { s -> s.toInt() }
            colorString(Color(t[0], t[1], t[2]))
        }

        return format
    }

    private fun colorString(color: Color) = colorString(Integer.toHexString(color.rgb).substring(2).toLowerCase())
    private fun colorString(hex: String) = "${C}x${hex.toCharArray().joinToString("") { z -> "${C}$z" }}"

}