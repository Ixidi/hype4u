package xyz.ixidi.hype4u.spigot.framework.config.field.reader.base

import org.bukkit.configuration.ConfigurationSection
import xyz.ixidi.hype4u.spigot.framework.config.field.reader.FieldReader

object DoubleFieldReader : FieldReader<Double> {

    override fun ConfigurationSection.load(key: String): Double? = if (isDouble(key)) getDouble(key) else null

}