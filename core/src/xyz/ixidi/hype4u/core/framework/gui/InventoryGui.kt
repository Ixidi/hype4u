package xyz.ixidi.hype4u.core.framework.gui

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import xyz.ixidi.hype4u.core.framework.util.color

abstract class InventoryGui(
    rows: Int,
    title: String = ""
) : Gui {

    val rows = if (rows > 6) 6 else if (rows < 1) 1 else rows
    val size = this.rows * 9

    private val inventory = Bukkit.createInventory(null, size, title.color())
    private val slots = HashMap<Int, GuiItem>()
    private var initialized = false

    protected fun setSlot(slot: Int, guiItem: GuiItem) {
        if (slot < 0 || slot >= size) throw ArrayIndexOutOfBoundsException()

        inventory.setItem(slot, guiItem.itemStack)
        slots[slot] = guiItem
    }

    protected fun fill(guiItem: GuiItem = GuiPredefined.FILL_GUI_ITEM) {
        for (i in 0 until size) {
            setSlot(i, guiItem)
        }
    }

    protected fun fillEmpty(guiItem: GuiItem = GuiPredefined.FILL_GUI_ITEM) {
        for (i in 0 until size) {
            if (!slots.containsKey(i)) setSlot(i, guiItem)
        }
    }

    protected fun clear() {
        slots.clear()
        inventory.clear()
    }

    final override fun open(player: Player) {
        if (!initialized) {
            initialized = true
            initialize()
        }
        player.openInventory(inventory)
    }

    final override fun handleClick(event: InventoryClickEvent): Behaviour {
        event.isCancelled = true
        return (slots[event.slot]?.guiAction ?: GuiAction.NOTHING).handleClick(event)
    }

    protected abstract fun initialize()
}