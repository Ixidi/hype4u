package xyz.ixidi.hype4u.core

import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.koin.core.KoinComponent
import org.koin.core.module.Module
import xyz.ixidi.hype4u.core.api.CoreApi
import xyz.ixidi.hype4u.core.api.CoreApiImpl
import xyz.ixidi.hype4u.core.database.punishment.PunishmentTable
import xyz.ixidi.hype4u.core.database.user.UserTable
import xyz.ixidi.hype4u.core.database.usergroup.UsersGroupsTable
import xyz.ixidi.hype4u.core.misc.CoreTranslatableKey
import xyz.ixidi.hype4u.core.module.commandModule
import xyz.ixidi.hype4u.core.module.configModule
import xyz.ixidi.hype4u.core.module.coreModule
import xyz.ixidi.hype4u.core.module.listenerModule
import xyz.ixidi.hype4u.framework.plugin.HypePlugin
import xyz.ixidi.hype4u.framework.plugin.SimplePluginInfo

class CorePlugin : HypePlugin<CoreApi>(PluginInfo) {

    private object PluginInfo : SimplePluginInfo(
        translatableKeys = CoreTranslatableKey.values().toList(),
        databaseTables = listOf(PunishmentTable, UserTable, UsersGroupsTable)
    )

    override fun getPublicApi(): CoreApi = CoreApiImpl()

    override fun getKoinModules(): List<Module> = listOf(coreModule, configModule, commandModule, listenerModule)

}