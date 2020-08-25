package xyz.ixidi.hype4u.spigot.framework.config.field.reader.base

import org.bukkit.configuration.ConfigurationSection
import xyz.ixidi.hype4u.spigot.framework.config.field.reader.FieldReader

object BooleanFieldReader : FieldReader<Boolean> {

    override fun ConfigurationSection.load(key: String): Boolean? = if (isBoolean(key)) getBoolean(key) else null

}