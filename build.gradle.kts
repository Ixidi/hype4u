plugins {
    kotlin("jvm") version "1.3.72" apply false
    id("com.github.johnrengelman.shadow") version "5.1.0" apply false
}

allprojects {

    group = "xyz.ixidi"
    version = "1.0"

}

subprojects {

    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        mavenCentral()
        jcenter()
        maven { url = java.net.URI("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
        maven { url = java.net.URI("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { url = java.net.URI("https://repo.extendedclip.com/content/repositories/placeholderapi/") }
        maven { url = java.net.URI("https://jitpack.io") }
    }

    tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        archiveBaseName.set("hypeplugin-${this@subprojects.name}")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

}
