package xyz.ixidi.hype4u.spigot.framework.gui

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import xyz.ixidi.hype4u.spigot.framework.gui.GuiManager

class GuiListener(
    private val guiManager: GuiManager
) : Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    fun onClose(event: InventoryCloseEvent) {
        val player = event.player as? Player ?: return

        val gui = guiManager.get(player) ?: return
        gui.handleClose(event).dueToClose(guiManager, gui, event)
    }

    @EventHandler(priority = EventPriority.NORMAL)
    fun onClick(event: InventoryClickEvent) {
        val player = event.whoClicked as? Player ?: return
        if (event.clickedInventory == null) return

        val gui = guiManager.get(player) ?: return
        gui.handleClick(event).dueToClick(guiManager, gui, event)
    }

}