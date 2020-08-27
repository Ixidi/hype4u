package xyz.ixidi.hype4u.spigot.framework.gui

import org.bukkit.event.inventory.InventoryClickEvent

interface GuiAction {

    companion object {

        val NOTHING: GuiAction = object : GuiAction {
            override fun handleClick(event: InventoryClickEvent): Behaviour = Behaviours.STAY_OPEN
        }

    }

    fun handleClick(event: InventoryClickEvent): Behaviour

}