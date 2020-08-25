package xyz.ixidi.hype4u.spigot.framework.config.field.reader

import org.bukkit.configuration.ConfigurationSection

interface FieldReader<T> {

    fun ConfigurationSection.load(key: String): T?

}