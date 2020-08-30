package xyz.ixidi.hype4u.framework.util.extension

import xyz.ixidi.hype4u.framework.util.ColorsFormatter

fun String.color() = ColorsFormatter.formatColors(this)