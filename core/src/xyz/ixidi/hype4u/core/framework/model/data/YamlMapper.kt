package xyz.ixidi.hype4u.core.framework.model.data

import java.io.File
import kotlin.reflect.KClass

interface YamlMapper {

    fun <T : Any> read(file: File, clazz: KClass<T>): T
    fun <T : Any> readOrWriteDefault(file: File, default: T, clazz: KClass<T>): T
    fun <T : Any> write(file: File, value: T)

}

inline fun <reified T : Any> YamlMapper.read(file: File) = read(file, T::class)
inline fun <reified T : Any> YamlMapper.readOrWriteDefault(file: File, default: T) = readOrWriteDefault(file, default, T::class)