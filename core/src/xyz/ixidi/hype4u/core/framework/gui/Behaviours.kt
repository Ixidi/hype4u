package xyz.ixidi.hype4u.core.framework.gui

import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class Behaviours private constructor(private val delegate: Behaviour): Behaviour by delegate {

    companion object {

        val CLOSE = object : Behaviour {
            override fun dueToClick(guiManager: GuiManager, gui: Gui, event: InventoryClickEvent) {
                guiManager.remove(event.whoClicked as Player)
                event.whoClicked.closeInventory()
            }

            override fun dueToClose(guiManager: GuiManager, gui: Gui, event: InventoryCloseEvent) {
                guiManager.remove(event.player as Player)
            }
        }.run { Behaviours(this) }

        val STAY_OPEN = object : Behaviour {
            override fun dueToClick(guiManager: GuiManager, gui: Gui, event: InventoryClickEvent) {}

            override fun dueToClose(guiManager: GuiManager, gui: Gui, event: InventoryCloseEvent) {
                guiManager.remove(event.player as Player)
                guiManager.open(event.player as Player, gui)
            }
        }.run { Behaviours(this) }

        val REFRESH = object : Behaviour {
            override fun dueToClick(guiManager: GuiManager, gui: Gui, event: InventoryClickEvent) {
                gui.refresh()
            }

            override fun dueToClose(guiManager: GuiManager, gui: Gui, event: InventoryCloseEvent) {
                gui.refresh()
                guiManager.open(event.player as Player, gui)
            }
        }.run { Behaviours(this) }

        fun openGui(toOpen: Gui): Behaviour = object : Behaviour {

            override fun dueToClick(guiManager: GuiManager, gui: Gui, event: InventoryClickEvent) {
                guiManager.remove(event.whoClicked as Player)
                event.whoClicked.closeInventory()
                guiManager.open(event.whoClicked as Player, toOpen)
            }

            override fun dueToClose(guiManager: GuiManager, gui: Gui, event: InventoryCloseEvent) {
                guiManager.remove(event.player as Player)
                guiManager.open(event.player as Player, toOpen)
            }

        }
    }

    fun thenExecute(run: () -> Unit): Behaviour = object : Behaviour {

        override fun dueToClick(guiManager: GuiManager, gui: Gui, event: InventoryClickEvent) {
            delegate.dueToClick(guiManager, gui, event)
            run()
        }

        override fun dueToClose(guiManager: GuiManager, gui: Gui, event: InventoryCloseEvent) {
            delegate.dueToClose(guiManager, gui, event)
            run()
        }

    }

}