package xyz.ixidi.hype4u.spigot.framework.config.field.io

import org.bukkit.configuration.ConfigurationSection

class MapFieldIO<T>(
    private val fieldIO: FieldIO<T>
) : FieldIO<Map<String, T>> {

    override fun ConfigurationSection.load(key: String): Map<String, T>? {
        val map = HashMap<String, T>()
        val section = getConfigurationSection(key) ?: return null

        section.getKeys(false).forEach {
            section.getConfigurationSection(it)!!.run {
                map[it] = fieldIO.run { load(it) } ?: return null
            }
        }

        return map
    }

    override fun ConfigurationSection.save(key: String, value: Map<String, T>) {
        val section = createSection(key)
        value.forEach { (t, u) ->
            section.createSection(t).run {
                fieldIO.run { save(t, u) }
            }
        }
    }

}