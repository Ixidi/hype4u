package xyz.ixidi.hype4u.framework.config

import org.bukkit.configuration.file.YamlConfiguration
import xyz.ixidi.hype4u.framework.config.field.ConfigField
import xyz.ixidi.hype4u.framework.config.field.io.*
import java.io.File

abstract class AbstractConfiguration(
    private val initConfigFile: () -> File
) {

    private val fieldList = ArrayList<ConfigField<*>>()
    private val file by lazy { initConfigFile() }

    protected fun <T> field(fieldIO: FieldIO<T>, key: String, default: T): ConfigField<T> {
        val field = ConfigField(fieldIO, key, default)
        fieldList.add(field)
        return field
    }

    protected fun stringField(key: String, default: String) = field(StringFieldIO, key, default)
    protected fun intField(key: String, default: Int) = field(IntFieldIO, key, default)
    protected fun doubleField(key: String, default: Double) = field(DoubleFieldIO, key, default)
    protected fun booleanField(key: String, default: Boolean) = field(BooleanFieldIO, key, default)

    protected fun <T> mapField(fieldIO: FieldIO<T>, key: String, default: Map<String, T>) = field(MapFieldIO(fieldIO), key, default)

    fun load() {
        val config = YamlConfiguration.loadConfiguration(file)
        for (field in fieldList) {
            field.load(config)
        }
    }

    fun save() {
        val config = YamlConfiguration()
        for (field in fieldList) {
            field.save(config)
        }

        config.save(file)
    }


}