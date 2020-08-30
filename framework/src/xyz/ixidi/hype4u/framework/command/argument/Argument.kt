package xyz.ixidi.hype4u.framework.command.argument

import xyz.ixidi.hype4u.framework.command.argument.parser.ArgumentParser

internal class Argument<T : Any>(
    val name: String,
    val description: String,
    val isGreedyString: Boolean,
    val default: String?,
    val isNullable: Boolean,
    val parser: ArgumentParser<T>
)