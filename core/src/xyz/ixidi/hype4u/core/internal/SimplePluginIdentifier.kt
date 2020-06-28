package xyz.ixidi.hype4u.core.internal

import org.bukkit.Material
import xyz.ixidi.hype4u.core.framework.PluginIdentifier

internal class SimplePluginIdentifier(
    override val name: String,
    override val description: String,
    override val guiIconMaterial: Material
) : PluginIdentifier