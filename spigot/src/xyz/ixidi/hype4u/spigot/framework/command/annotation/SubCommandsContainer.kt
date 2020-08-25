package xyz.ixidi.hype4u.spigot.framework.command.annotation

@Retention
@Target(AnnotationTarget.CLASS)
annotation class SubCommandsContainer(val command: Command)