plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.koin:koin-core:2.0.1")
    implementation("org.koin:koin-core-ext:2.0.1")
    implementation("org.jetbrains.exposed", "exposed-core", "0.25.1")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.25.1")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.25.1")
    compileOnly("org.spigotmc:spigot-api:1.16.1-R0.1-SNAPSHOT")
    implementation("mysql:mysql-connector-java:8.0.20")
}

sourceSets {
    main {
        java.setSrcDirs(listOf("src"))
        resources.setSrcDirs(listOf("res"))
    }
}