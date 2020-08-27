package xyz.ixidi.hype4u.spigot.injection.module

import com.mysql.cj.jdbc.MysqlDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.dsl.module
import xyz.ixidi.hype4u.spigot.config.PluginConfig
import xyz.ixidi.hype4u.spigot.core.database.punishment.PunishmentTable
import xyz.ixidi.hype4u.spigot.core.database.user.UserTable
import xyz.ixidi.hype4u.spigot.core.database.usergroup.UsersGroupsTable

val databaseModule = module {

    single {
        val source = get<PluginConfig>().run {
            MysqlDataSource().apply {
                serverName = mysqlHost
                port = mysqlPort
                databaseName = mysqlDatabase
                user = mysqlUser
                password = mysqlPassword
                serverTimezone = "UTC"
                useSSL = false
            }
        }

        Database.connect(source).also {
            transaction(it) {
                SchemaUtils.createMissingTablesAndColumns(PunishmentTable, UserTable, UsersGroupsTable)
            }
        }

    }

}