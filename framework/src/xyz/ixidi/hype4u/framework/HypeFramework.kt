package xyz.ixidi.hype4u.framework

import xyz.ixidi.hype4u.framework.plugin.PluginApi
import kotlin.reflect.KClass

object HypeFramework {

    private val apiMap = HashMap<KClass<*>, PluginApi>()

    internal fun <T : PluginApi> registerApi(clazz: KClass<out T>, api: T) {
        apiMap[clazz] = api
    }

    fun <T : PluginApi> getApi(clazz: KClass<T>): T? = apiMap[clazz] as? T

}