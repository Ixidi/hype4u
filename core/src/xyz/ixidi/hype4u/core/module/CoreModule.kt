package xyz.ixidi.hype4u.core.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.core.config.GroupConfig
import xyz.ixidi.hype4u.core.group.GroupManager
import xyz.ixidi.hype4u.core.group.GroupManagerImpl
import xyz.ixidi.hype4u.core.group.permission.PermissionsManager
import xyz.ixidi.hype4u.core.group.permission.PermissionsManagerImpl
import xyz.ixidi.hype4u.core.punishment.PunishmentManager
import xyz.ixidi.hype4u.core.punishment.PunishmentManagerImpl
import xyz.ixidi.hype4u.core.repository.punishment.DatabasePunishmentRepository
import xyz.ixidi.hype4u.core.repository.punishment.PunishmentRepository
import xyz.ixidi.hype4u.core.repository.usegroups.DatabaseUserGroupsRepository
import xyz.ixidi.hype4u.core.repository.usegroups.UserGroupsRepository
import xyz.ixidi.hype4u.core.repository.user.DatabaseUserRepository
import xyz.ixidi.hype4u.core.repository.user.UserRepository
import xyz.ixidi.hype4u.core.user.UserManager
import xyz.ixidi.hype4u.core.user.UserManagerImpl

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

    single<PunishmentManager> { PunishmentManagerImpl(get(), get(), get()) }
    single<UserManager> { UserManagerImpl() }

}