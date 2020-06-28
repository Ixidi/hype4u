package xyz.ixidi.hype4u.core.framework.command.rich.argument.handler

import kotlin.reflect.KClass

interface ArgumentHandlerManager {

    fun <T : Any, H : ArgumentHandler<T>> registerHandler(valueClass: KClass<T>, handlerClass: KClass<H>, handler: ArgumentHandler<T>)
    fun getRegisteredHandler(valueClass: KClass<*>, handlerClass: KClass<*>): ArgumentHandler<*>?

}