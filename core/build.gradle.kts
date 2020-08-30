plugins {
    kotlin("jvm")
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("org.koin:koin-core:2.0.1")
    compileOnly("org.koin:koin-core-ext:2.0.1")
    compileOnly("org.jetbrains.exposed", "exposed-core", "0.25.1")
    compileOnly("org.jetbrains.exposed", "exposed-dao", "0.25.1")
    compileOnly("org.jetbrains.exposed", "exposed-jdbc", "0.25.1")
    compileOnly("org.spigotmc:spigot-api:1.16.1-R0.1-SNAPSHOT")
    compileOnly(project(":framework"))
}

sourceSets {
    main {
        java.setSrcDirs(listOf("src"))
        resources.setSrcDirs(listOf("res"))
    }
}