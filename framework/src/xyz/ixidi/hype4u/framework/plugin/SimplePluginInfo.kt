package xyz.ixidi.hype4u.framework.plugin

import org.jetbrains.exposed.sql.Table
import xyz.ixidi.hype4u.framework.language.TranslatableKey

open class SimplePluginInfo(
    final override val translatableKeys: List<TranslatableKey> = emptyList(),
    final override val databaseTables: List<Table> = emptyList()
) : PluginInfo