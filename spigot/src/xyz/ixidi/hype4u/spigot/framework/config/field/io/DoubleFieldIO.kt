package xyz.ixidi.hype4u.spigot.framework.config.field.io

import org.bukkit.configuration.ConfigurationSection

object DoubleFieldIO : FieldIO<Double> {

    override fun ConfigurationSection.load(key: String): Double? = if (isDouble(key)) getDouble(key) else null

    override fun ConfigurationSection.save(key: String, value: Double) = set(key, value)

}