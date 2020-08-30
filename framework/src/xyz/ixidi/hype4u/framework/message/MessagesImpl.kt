package xyz.ixidi.hype4u.framework.message

import org.bukkit.ChatColor
import xyz.ixidi.hype4u.framework.language.TranslatableKey
import xyz.ixidi.hype4u.framework.language.TranslationManager
import xyz.ixidi.hype4u.framework.util.extension.color

internal class MessagesImpl(
    private val translationManager: TranslationManager
) : Messages {

    override fun getRawMessage(translatableKey: TranslatableKey, vararg parameters: Pair<String, Any?>): String {
        var message = translationManager.getTranslation(translatableKey)
        parameters.forEach { (k, v) -> message = message.replace("{$k}", "$v") }

        return message
    }

    override fun getColoredMessage(translatableKey: TranslatableKey, vararg parameters: Pair<String, Any?>): String = getRawMessage(translatableKey, *parameters).color()

    override fun getStrippedMessage(translatableKey: TranslatableKey, vararg parameters: Pair<String, Any?>): String =
        ChatColor.stripColor(getColoredMessage(translatableKey, *parameters))!!

}