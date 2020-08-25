package xyz.ixidi.hype4u.spigot.framework.command.argument

import xyz.ixidi.hype4u.spigot.framework.command.argument.parser.ArgumentParser

class Argument<T : Any>(
    val name: String,
    val description: String,
    val isGreedyString: Boolean,
    val default: String?,
    val isNullable: Boolean,
    val parser: ArgumentParser<T>
)