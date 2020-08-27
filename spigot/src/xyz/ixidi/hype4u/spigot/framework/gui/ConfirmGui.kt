package xyz.ixidi.hype4u.spigot.framework.gui

/*import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import xyz.ixidi.hype4u.spigot.framework.util.itemStack

abstract class ConfirmGui(
    guiTitle: String,
    private val question: String,
    private val config: Configuration.Islands.Lang.Gui.Confirm,
    private val questionLore: List<String> = emptyList()
) : InventoryGui(3, guiTitle) {

    companion object {

        private val DENY_SLOTS = listOf(0, 1, 2, 9, 10, 11, 18, 19, 20)
        private val CONFIRM_SLOTS = listOf(6, 7, 8, 15, 16, 17, 24, 25, 26)
        private val PAPER_SLOT = 13

    }

    final override fun initialize() {
        val denyItem = GuiItem(
            itemStack = itemStack(
                material = config.denyItem,
                name = config.deny.color()
            ),
            guiAction = object : GuiAction {
                override fun handleClick(event: InventoryClickEvent): Behaviour = deny(event.whoClicked as Player)
            }
        )
        DENY_SLOTS.forEach { setSlot(it, denyItem) }

        val confirmItem = GuiItem(
            itemStack = itemStack(
                material = config.confirmItem,
                name = config.confirm.color()
            ),
            guiAction = object : GuiAction {
                override fun handleClick(event: InventoryClickEvent): Behaviour = confirm(event.whoClicked as Player)
            }
        )
        CONFIRM_SLOTS.forEach { setSlot(it, confirmItem) }

        val paperItem = GuiItem(
            itemStack(
                material = Material.PAPER,
                name = question,
                lore = questionLore
            )
        )
        setSlot(PAPER_SLOT, paperItem)

        fillEmpty()
    }

    final override fun refresh() {}

    final override fun handleClose(event: InventoryCloseEvent): Behaviour = deny(event.player as Player)

    protected abstract fun confirm(player: Player): Behaviour
    protected abstract fun deny(player: Player): Behaviour

}*/