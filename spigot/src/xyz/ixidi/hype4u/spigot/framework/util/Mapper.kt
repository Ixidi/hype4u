package xyz.ixidi.hype4u.spigot.framework.util

internal interface Mapper<F, T> {

    fun F.map(): T

}

internal fun <F, T> F.map(mapper: Mapper<F, T>) = mapper.run { map() }