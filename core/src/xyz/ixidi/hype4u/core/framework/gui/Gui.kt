package xyz.ixidi.hype4u.core.framework.gui

import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

interface Gui {

    fun open(player: Player)

    fun refresh()

    fun handleClose(event: InventoryCloseEvent): Behaviour

    fun handleClick(event: InventoryClickEvent): Behaviour

}