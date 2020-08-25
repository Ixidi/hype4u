package xyz.ixidi.hype4u.spigot.framework.config

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import xyz.ixidi.hype4u.spigot.framework.config.field.reader.base.BooleanFieldReader
import xyz.ixidi.hype4u.spigot.framework.config.field.ConfigField
import xyz.ixidi.hype4u.spigot.framework.config.field.reader.base.DoubleFieldReader
import xyz.ixidi.hype4u.spigot.framework.config.field.reader.FieldReader
import xyz.ixidi.hype4u.spigot.framework.config.field.reader.base.IntFieldReader
import xyz.ixidi.hype4u.spigot.framework.config.field.reader.base.StringFieldReader
import java.io.File

abstract class AbstractConfiguration(
    private val initConfigFile: () -> File
) {

    private val fieldList = ArrayList<ConfigField<*, *>>()

    protected fun <T, V> field(fieldReader: FieldReader<T>, key: String, default: T, transformer: (T) -> V): ConfigField<T, V> {
        val field = ConfigField(fieldReader, key, default, transformer)
        fieldList.add(field)
        return field
    }

    protected fun <T> field(fieldReader: FieldReader<T>, key: String, default: T) = field(fieldReader, key, default) { it }

    protected fun <V> stringField(key: String, default: String, transformer: (String) -> V) = field(StringFieldReader, key, default, transformer)
    protected fun stringField(key: String, default: String) = stringField(key, default) {it}

    protected fun <V> intField(key: String, default: Int, transformer: (Int) -> V) = field(IntFieldReader, key, default, transformer)
    protected fun intField(key: String, default: Int) = intField(key, default) {it}

    protected fun <V> doubleField(key: String, default: Double, transformer: (Double) -> V) = field(DoubleFieldReader, key, default, transformer)
    protected fun doubleField(key: String, default: Double) = doubleField(key, default) {it}

    protected fun <V> booleanField(key: String, default: Boolean, transformer: (Boolean) -> V) = field(
        BooleanFieldReader, key, default, transformer)
    protected fun booleanField(key: String, default: Boolean) = booleanField(key, default) {it}

    protected fun <V> mapField(key: String, default: Map<String, V>, valueCreator: ConfigurationSection.() -> V) = field(object :
        FieldReader<Map<String, V>> {

        //TODO move to separated class

        override fun ConfigurationSection.load(key: String): Map<String, V>? {
            val section = getConfigurationSection(key) ?: return null

            val map = HashMap<String, V>()
            section.getKeys(false).forEach {
                val s = section.getConfigurationSection(it) ?: throw ConfigException("Configuration section expected at $key.$it")

                map[it] = valueCreator(s)
            }

            return map
        }

    }, key, default, {it})

    fun load() {
        val file = initConfigFile()
        val config = YamlConfiguration.loadConfiguration(file)

        var saveFile = false
        for (field in fieldList) {
            if (field.load(config) && !saveFile) {
                saveFile = true
            }
        }

    }


}