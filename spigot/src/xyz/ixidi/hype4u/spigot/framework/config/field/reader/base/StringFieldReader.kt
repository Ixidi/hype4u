package xyz.ixidi.hype4u.spigot.framework.config.field.reader.base

import org.bukkit.configuration.ConfigurationSection
import xyz.ixidi.hype4u.spigot.framework.config.field.reader.FieldReader

object StringFieldReader : FieldReader<String> {

    override fun ConfigurationSection.load(key: String): String? = getString(key)

}