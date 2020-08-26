package xyz.ixidi.hype4u.spigot.framework.config.field.io

import org.bukkit.configuration.ConfigurationSection

object StringFieldIO : FieldIO<String> {

    override fun ConfigurationSection.load(key: String): String? = getString(key)

    override fun ConfigurationSection.save(key: String, value: String) = set(key, value)

}