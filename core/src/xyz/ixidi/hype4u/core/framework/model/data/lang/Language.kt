package xyz.ixidi.hype4u.core.framework.model.data.lang

import xyz.ixidi.hype4u.core.framework.util.YamlFile

interface Language {

    fun string(key: String, vararg parameters: Pair<String, Any?>): String
    fun registerStringYaml(name: String, yaml: YamlFile)
    fun reload()

}