package xyz.ixidi.hype4u.core.internal.config

internal data class Configuration(
    val mySQL: MySQL = MySQL()
) {

    data class MySQL(
        val host: String = "localhost",
        val port: Int = 3306,
        val username: String = "root",
        val password: String = "",
        val database: String = "hypeCore"
    )

}