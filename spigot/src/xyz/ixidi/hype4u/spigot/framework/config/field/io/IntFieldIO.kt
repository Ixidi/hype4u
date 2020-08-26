package xyz.ixidi.hype4u.spigot.framework.config.field.io

import org.bukkit.configuration.ConfigurationSection

object IntFieldIO : FieldIO<Int> {

    override fun ConfigurationSection.load(key: String): Int? = if (isInt(key)) getInt(key) else null

    override fun ConfigurationSection.save(key: String, value: Int) = set(key, value)
}