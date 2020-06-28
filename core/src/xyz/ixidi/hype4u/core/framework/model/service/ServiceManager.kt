package xyz.ixidi.hype4u.core.framework.model.service

import kotlin.reflect.KClass

interface ServiceProvider {

    fun <T : Any> registerService(clazz: KClass<T>, impl: T)
    fun <T : Any> getService(clazz: KClass<T>): T

}

inline fun <reified T: Any> ServiceProvider.registerService(impl: T) = registerService(T::class, impl)
inline fun <reified T: Any> ServiceProvider.getService() = getService(T::class)