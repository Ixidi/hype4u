package xyz.ixidi.hype4u.spigot.framework.config.field.reader.base

import org.bukkit.configuration.ConfigurationSection
import xyz.ixidi.hype4u.spigot.framework.config.field.reader.FieldReader

object IntFieldReader : FieldReader<Int> {

    override fun ConfigurationSection.load(key: String): Int? = if (isInt(key)) getInt(key) else null

}