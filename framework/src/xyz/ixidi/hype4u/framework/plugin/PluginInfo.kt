package xyz.ixidi.hype4u.framework.plugin

import org.jetbrains.exposed.sql.Table
import xyz.ixidi.hype4u.framework.language.TranslatableKey

interface PluginInfo {

    val translatableKeys: List<TranslatableKey>
    val databaseTables: List<Table>

}