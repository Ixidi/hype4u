package xyz.ixidi.hype4u.spigot.injection.module

import com.mysql.cj.jdbc.MysqlDataSource
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module
import xyz.ixidi.hype4u.spigot.config.PluginConfig

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

        Database.connect(source)
    }

}