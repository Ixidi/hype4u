package xyz.ixidi.hype4u.framework.language

import org.bukkit.configuration.file.YamlConfiguration
import xyz.ixidi.hype4u.framework.config.PluginConfig
import xyz.ixidi.hype4u.framework.repository.PluginRepository

internal class TranslationManagerImpl(
    private val pluginConfig: PluginConfig
) : TranslationManager {

    class LanguageFileNotFoundException(val language: Language) : Exception("Language file language_${language.code}.yml of ${language.englishName} language has not been found.")
    class TranslatableKeyNotImplementedException(val language: Language, val translatableKey: TranslatableKey) : Exception("Language file language_${language.code}.yml of ${language.englishName} language does not implement ${translatableKey.key} key.")

    private val translations = HashMap<TranslatableKey, String>()

    override fun getTranslation(translatableKey: TranslatableKey): String = translations[translatableKey]!!

    override fun load(keys: List<TranslatableKey>, pluginRepository: PluginRepository) {
        val file = pluginRepository.getLocalResourceFile("language_${pluginConfig.language.code}.yml") ?: throw LanguageFileNotFoundException(pluginConfig.language)
        val config = YamlConfiguration.loadConfiguration(file)

        translations.clear()
        keys.forEach {
            val string = config.getString(it.key) ?: throw TranslatableKeyNotImplementedException(pluginConfig.language, it)

            translations[it] = string
        }
    }


}