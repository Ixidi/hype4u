package xyz.ixidi.hype4u.core.internal.injection.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.core.framework.model.data.YamlMapper
import xyz.ixidi.hype4u.core.framework.model.data.readOrWriteDefault
import xyz.ixidi.hype4u.core.internal.config.Configuration
import java.io.File

internal fun dataModule(dataFolder: File) = module {

    single {
        val configFile = File(dataFolder, "config.yml")

        get<YamlMapper>().readOrWriteDefault(configFile, Configuration())
    }

    /*single(createdAtStart = true) {
        get<Configuration>().mySQL.let { config ->
            Database.connect(
                MysqlDataSource().apply {
                    serverName = config.host
                    port = config.port
                    user = config.username
                    if (config.password.isNotBlank()) password = config.password
                    useSSL = false
                    serverTimezone = "UTC"
                    databaseName = config.database
                }
            )
        }
    }*/

}