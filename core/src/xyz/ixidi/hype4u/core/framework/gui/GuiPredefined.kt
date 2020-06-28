package xyz.ixidi.hype4u.core.framework.gui

import org.bukkit.Material
import xyz.ixidi.hype4u.core.framework.util.itemStack

object GuiPredefined {

    val FILL_ITEM = itemStack(
        material = Material.GRAY_STAINED_GLASS_PANE,
        name = " "
    )
    val FILL_GUI_ITEM = GuiItem(FILL_ITEM)

    val NEXT_ITEM = itemStack(
        material = Material.STICK,
        name = "&l-->"
    )

    val PREVIOUS_ITEM = itemStack(
        material = Material.STICK,
        name = "&l<--"
    )

}