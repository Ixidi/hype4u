package xyz.ixidi.hype4u.spigot.framework.config.field

import org.bukkit.configuration.ConfigurationSection
import xyz.ixidi.hype4u.spigot.framework.config.field.reader.FieldReader
import kotlin.reflect.KProperty

class ConfigField<T, V>(
    private val Reader: FieldReader<T>,
    private val key: String,
    private val default: T,
    private val transform: (T) -> V
) {

    private var value: V? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): V {
        return value ?: throw Exception("Config has not been loaded.")
    }

    /**
     * Returns if default value has been set.
     */
    fun load(config: ConfigurationSection): Boolean {
        val v = Reader.run { config.load(key) }

        if (v == null) {
            value = transform(default)
            return true
        }

        value = transform(v)
        return false
    }

}