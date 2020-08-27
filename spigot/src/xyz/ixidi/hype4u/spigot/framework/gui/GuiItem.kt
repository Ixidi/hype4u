package xyz.ixidi.hype4u.spigot.framework.gui

import org.bukkit.inventory.ItemStack
import xyz.ixidi.hype4u.spigot.framework.gui.GuiAction

data class GuiItem(
    val itemStack: ItemStack,
    val guiAction: GuiAction = GuiAction.NOTHING
)