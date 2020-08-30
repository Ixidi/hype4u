package xyz.ixidi.hype4u.framework.util

interface Mapper<F, T> {

    fun F.map(): T

}

fun <F, T> F.map(mapper: Mapper<F, T>) = mapper.run { map() }