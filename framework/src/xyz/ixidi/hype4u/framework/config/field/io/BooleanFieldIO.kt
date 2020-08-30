package xyz.ixidi.hype4u.framework.config.field.io

import org.bukkit.configuration.ConfigurationSection

object BooleanFieldIO : FieldIO<Boolean> {

    override fun ConfigurationSection.load(key: String): Boolean? = if (isBoolean(key)) getBoolean(key) else null

    override fun ConfigurationSection.save(key: String, value: Boolean) = set(key, value)

}