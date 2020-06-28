package xyz.ixidi.hype4u.core.internal.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import xyz.ixidi.hype4u.core.framework.model.data.YamlMapper
import java.io.File
import kotlin.reflect.KClass

internal class JacksonYamlMapper : YamlMapper {

    private val mapper by lazy {
        ObjectMapper(YAMLFactory()).apply {
            registerModule(KotlinModule())
        }
    }

    override fun <T : Any> read(file: File, clazz: KClass<T>): T {
        return mapper.readValue(file, clazz.java)
    }

    override fun <T : Any> readOrWriteDefault(file: File, default: T, clazz: KClass<T>): T {
        if (!file.exists()) {
            file.createNewFile()
            mapper.writeValue(file, default)

            return default
        }

        return read(file, clazz)
    }

    override fun <T : Any> write(file: File, value: T) {
        if (!file.exists()) {
            file.createNewFile()
        }

        mapper.writeValue(file, value)
    }

}