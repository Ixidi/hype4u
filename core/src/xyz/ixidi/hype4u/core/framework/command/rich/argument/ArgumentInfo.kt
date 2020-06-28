package xyz.ixidi.hype4u.core.framework.command.rich.argument

import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandler

internal data class ArgumentInfo(
    val name: String,
    val handler: ArgumentHandler<*>,
    val isVararg: Boolean,
    val isNullable: Boolean
)