package xyz.ixidi.hype4u.core.internal.data

import xyz.ixidi.hype4u.core.framework.model.data.lang.Language
import xyz.ixidi.hype4u.core.framework.util.YamlFile

internal class SimpleLanguage : Language {

    private val stringMap = HashMap<String, String>()
    private val yamlFiles = HashMap<String, YamlFile>()

    override fun string(key: String, vararg parameters: Pair<String, Any?>): String {
        var string = stringMap[key] ?: return key
        parameters.forEach { (k, v) -> string = string.replace("{$k}", "$v") }

        return string
    }

    override fun registerStringYaml(name: String, yaml: YamlFile) {
        if (yamlFiles.containsKey(name)) {
            val iterator = stringMap.iterator()
            for (entry in iterator) {
                if (entry.key.startsWith(name)) iterator.remove()
            }
        }

        yamlFiles[name] = yaml
        load(name, yaml)
    }

    override fun reload() {
        stringMap.clear()
        yamlFiles.forEach { (name, yaml) -> load(name, yaml) }
    }

    private fun load(name: String, yaml: YamlFile) {
        yaml.load()

        val keys = yaml.getKeys(true)
        keys.forEach { stringMap["$name.$it"] = yaml.getString(it)!! }
    }
}