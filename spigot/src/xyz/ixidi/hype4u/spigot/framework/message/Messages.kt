package xyz.ixidi.hype4u.spigot.framework.message

import xyz.ixidi.hype4u.spigot.misc.TranslatableKey

interface Messages {

    fun getRawMessage(translatableKey: TranslatableKey, vararg parameters: Pair<String, Any?>): String
    fun getColoredMessage(translatableKey: TranslatableKey, vararg parameters: Pair<String, Any?>): String
    fun getStrippedMessage(translatableKey: TranslatableKey, vararg parameters: Pair<String, Any?>): String

}