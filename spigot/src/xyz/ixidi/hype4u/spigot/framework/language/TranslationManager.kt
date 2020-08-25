package xyz.ixidi.hype4u.spigot.framework.language

import xyz.ixidi.hype4u.spigot.misc.TranslatableKey

interface TranslationManager {

    fun getTranslation(translatableKey: TranslatableKey): String
    fun load(language: Language)

}