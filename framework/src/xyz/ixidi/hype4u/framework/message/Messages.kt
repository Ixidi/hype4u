package xyz.ixidi.hype4u.framework.message

import xyz.ixidi.hype4u.framework.language.TranslatableKey

interface Messages {

    fun getRawMessage(translatableKey: TranslatableKey, vararg parameters: Pair<String, Any?>): String
    fun getColoredMessage(translatableKey: TranslatableKey, vararg parameters: Pair<String, Any?>): String
    fun getStrippedMessage(translatableKey: TranslatableKey, vararg parameters: Pair<String, Any?>): String

}