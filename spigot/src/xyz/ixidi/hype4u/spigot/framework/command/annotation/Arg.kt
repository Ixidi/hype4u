package xyz.ixidi.hype4u.spigot.framework.command.annotation

@Retention
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Arg(
    val name: String,
    val desc: String = "Nie przewidziano opisu.",
    val def: String = ""
)