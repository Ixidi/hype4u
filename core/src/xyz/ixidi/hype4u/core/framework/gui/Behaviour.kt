package xyz.ixidi.hype4u.core.framework.gui

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

interface Behaviour {

    fun dueToClick(guiManager: GuiManager, gui: Gui, event: InventoryClickEvent)

    fun dueToClose(guiManager: GuiManager, gui: Gui, event: InventoryCloseEvent)

}