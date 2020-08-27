package xyz.ixidi.hype4u.spigot.injection.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.spigot.config.GroupConfig
import xyz.ixidi.hype4u.spigot.core.group.GroupManager
import xyz.ixidi.hype4u.spigot.core.group.GroupManagerImpl
import xyz.ixidi.hype4u.spigot.core.group.permission.PermissionsManager
import xyz.ixidi.hype4u.spigot.core.group.permission.PermissionsManagerImpl
import xyz.ixidi.hype4u.spigot.core.punishment.PunishmentManager
import xyz.ixidi.hype4u.spigot.core.punishment.PunishmentManagerImpl
import xyz.ixidi.hype4u.spigot.core.repository.punishment.DatabasePunishmentRepository
import xyz.ixidi.hype4u.spigot.core.repository.punishment.PunishmentRepository
import xyz.ixidi.hype4u.spigot.core.repository.usegroups.DatabaseUserGroupsRepository
import xyz.ixidi.hype4u.spigot.core.repository.usegroups.UserGroupsRepository
import xyz.ixidi.hype4u.spigot.core.repository.user.DatabaseUserRepository
import xyz.ixidi.hype4u.spigot.core.repository.user.UserRepository
import xyz.ixidi.hype4u.spigot.core.user.UserManager
import xyz.ixidi.hype4u.spigot.core.user.UserManagerImpl

val coreModule = module {

    single<PunishmentRepository> { DatabasePunishmentRepository(get()) }
    single<UserGroupsRepository> { DatabaseUserGroupsRepository(get(), get()) }
    single<UserRepository> { DatabaseUserRepository(get(), get(), get(), get(), get()) }

    single<PermissionsManager> { PermissionsManagerImpl(get(), get()) }
    single<GroupManager> {
        GroupManagerImpl().apply {
            get<GroupConfig>().groups.forEach { loadGroupFromDTO(it.value) }
        }
    }

    single<PunishmentManager> { PunishmentManagerImpl(get(), get()) }
    single<UserManager> { UserManagerImpl() }

}