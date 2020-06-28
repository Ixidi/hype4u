package xyz.ixidi.hype4u.core.framework

import org.bukkit.Material

interface PluginIdentifier {

    val name: String
    val description: String
    val guiIconMaterial: Material

}