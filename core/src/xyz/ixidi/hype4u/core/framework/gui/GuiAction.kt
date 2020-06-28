package xyz.ixidi.hype4u.core.framework.gui

import org.bukkit.event.inventory.InventoryClickEvent

interface GuiAction {

    companion object {

        val NOTHING: GuiAction = object : GuiAction {
            override fun handleClick(event: InventoryClickEvent): Behaviour = Behaviours.STAY_OPEN
        }

        fun openGui(gui: Gui) = object : GuiAction {
            override fun handleClick(event: InventoryClickEvent): Behaviour = Behaviours.openGui(gui)
        }

    }

    fun handleClick(event: InventoryClickEvent): Behaviour

}