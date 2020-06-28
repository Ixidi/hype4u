plugins {
    kotlin("jvm") version "1.3.72" apply false
    id("com.github.johnrengelman.shadow") version "5.1.0"
}

allprojects {

    group = "xyz.ixidi"
    version = "1.0"

    repositories {
        mavenCentral()
        jcenter()
        maven { url = java.net.URI("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
        maven { url = java.net.URI("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { url = java.net.URI("https://repo.extendedclip.com/content/repositories/placeholderapi/") }
        maven { url = java.net.URI("https://jitpack.io") }
    }

}
