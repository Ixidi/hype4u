package xyz.ixidi.hype4u.framework.language

import xyz.ixidi.hype4u.framework.repository.PluginRepository

interface TranslationManager {

    fun getTranslation(translatableKey: TranslatableKey): String
    fun load(keys: List<TranslatableKey>, pluginRepository: PluginRepository)

}