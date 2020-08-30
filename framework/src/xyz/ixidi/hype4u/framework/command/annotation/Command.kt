package xyz.ixidi.hype4u.framework.command.annotation

@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class Command(
    val name: String,
    val desc: String = "Nie przewidziano opisu.",
    val aliases: Array<String> = [],
    val permission: String = ""
)