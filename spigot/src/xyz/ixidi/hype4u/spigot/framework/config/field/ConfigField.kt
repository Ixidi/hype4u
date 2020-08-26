package xyz.ixidi.hype4u.spigot.framework.config.field

import org.bukkit.configuration.ConfigurationSection
import xyz.ixidi.hype4u.spigot.framework.config.field.io.FieldIO
import kotlin.reflect.KProperty

class ConfigField<T>(
    private val io: FieldIO<T>,
    private val key: String,
    private val default: T
) {

    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw Exception("Config has not been loaded.")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }

    /**
     * Returns if default value has been set.
     */
    fun load(config: ConfigurationSection) {
        val v = io.run { config.load(key) }

        if (v == null) {
            value = default
            return
        }

        value = v
        return
    }

    fun save(config: ConfigurationSection) {
        io.run { save(config) }
    }

}