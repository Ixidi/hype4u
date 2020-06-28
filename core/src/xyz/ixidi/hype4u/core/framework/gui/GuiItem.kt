package xyz.ixidi.hype4u.core.framework.gui

import org.bukkit.inventory.ItemStack

data class GuiItem(
    val itemStack: ItemStack,
    val guiAction: GuiAction = GuiAction.NOTHING
)