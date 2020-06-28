package xyz.ixidi.hype4u.core.framework.gui

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

abstract class SelectionGui<T : Any>(
    title: String,
    private val options: List<Option<T>>
) : InventoryGui(options.size / 9, title) {

    data class Option<T>(
        val value: T,
        val itemStack: ItemStack
    )

    private inner class Action(
        private val value: T
    ) : GuiAction {

        override fun handleClick(event: InventoryClickEvent): Behaviour = handleChoose(value, event)

    }

    private inner class NextPage : GuiAction {
        override fun handleClick(event: InventoryClickEvent): Behaviour {
            page++
            return Behaviours.REFRESH
        }
    }

    private inner class PreviousPage : GuiAction {
        override fun handleClick(event: InventoryClickEvent): Behaviour {
            page--
            return Behaviours.REFRESH
        }
    }

    private var page: Int = 0

    final override fun initialize() {
        if (options.size <= 54) {
            for ((slot, option) in options.withIndex()) {
                option.apply {
                    setSlot(slot, GuiItem(itemStack, Action(value)))
                }
            }
            fillEmpty()
        } else {
            refresh()
        }
    }

    final override fun refresh() {
        clear()
        val start = (page * 45)
        var end = start + 44
        if (end > options.size) end = options.size - 1

        var slot = 0
        for (optionIndex in start..end) {
            options[optionIndex].apply {
                setSlot(slot++, GuiItem(itemStack, Action(value)))
            }
        }

        if (page > 0) {
            setSlot(45, GuiItem(GuiPredefined.PREVIOUS_ITEM, PreviousPage()))
        }
        if (((page + 1) * 45) < options.size) {
            setSlot(53, GuiItem(GuiPredefined.NEXT_ITEM, NextPage()))
        }
        fillEmpty()
    }

    protected abstract fun handleChoose(chosen: T, clickEvent: InventoryClickEvent): Behaviour

}