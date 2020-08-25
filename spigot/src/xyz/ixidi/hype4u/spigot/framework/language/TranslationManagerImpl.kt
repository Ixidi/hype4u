package xyz.ixidi.hype4u.spigot.framework.language

import org.bukkit.configuration.file.YamlConfiguration
import xyz.ixidi.hype4u.spigot.framework.repository.PluginRepository
import xyz.ixidi.hype4u.spigot.misc.TranslatableKey

class TranslationManagerImpl(
    private val pluginRepository: PluginRepository
) : TranslationManager {

    class LanguageFileNotFoundException(val language: Language) : Exception("Language file language_${language.code}.yml of ${language.englishName} language has not been found.")
    class TranslatableKeyNotImplementedException(val language: Language, val translatableKey: TranslatableKey) : Exception("Language file language_${language.code}.yml of ${language.englishName} language does not implement ${translatableKey.key} key.")

    private val translations = HashMap<TranslatableKey, String>()

    override fun getTranslation(translatableKey: TranslatableKey): String = translations[translatableKey]!!

    override fun load(language: Language) {
        val file = pluginRepository.getLocalResourceFile("language_${language.code}.yml") ?: throw LanguageFileNotFoundException(language)
        val config = YamlConfiguration.loadConfiguration(file)

        translations.clear()
        TranslatableKey.values().forEach {
            val string = config.getString(it.key) ?: throw TranslatableKeyNotImplementedException(language, it)

            translations[it] = string
        }
    }


}