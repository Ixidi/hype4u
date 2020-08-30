package xyz.ixidi.hype4u.framework.util

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import xyz.ixidi.hype4u.framework.util.extension.color

fun itemStack(
    material: Material,
    amount: Int = 1,
    name: String? = null,
    lore: List<String> = emptyList(),
    enchants: Map<Enchantment, Int> = emptyMap(),
    flags: List<ItemFlag> = emptyList()
): ItemStack {
    val stack = ItemStack(material, amount)
    val meta = stack.itemMeta!!
    if (name != null) meta.setDisplayName(name.color())
    if (lore.isNotEmpty()) meta.lore = lore.map { it.color() }
    if (enchants.isNotEmpty()) enchants.forEach { (enchantment, level) -> meta.addEnchant(enchantment, level, true) }
    if (flags.isNotEmpty()) meta.addItemFlags(*flags.toTypedArray())

    stack.itemMeta = meta
    return stack
}