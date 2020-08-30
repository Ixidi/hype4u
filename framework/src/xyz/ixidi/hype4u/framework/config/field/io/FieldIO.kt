package xyz.ixidi.hype4u.framework.config.field.io

import org.bukkit.configuration.ConfigurationSection

interface FieldIO<T> {

    fun ConfigurationSection.load(key: String): T?

    fun ConfigurationSection.save(key: String, value: T)


}