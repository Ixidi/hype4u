package xyz.ixidi.hype4u.framework.command.argument.parser

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.framework.FrameworkTranslatableKey
import xyz.ixidi.hype4u.framework.util.TimeParser
import xyz.ixidi.hype4u.framework.util.extension.message
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

object DefaultDateParser : ArgumentParser<Date> {

    override val valueClass: KClass<Date> = Date::class

    override fun parse(commandSender: CommandSender, string: String): Date? = try {
        val time = TimeParser.parseTime(string, TimeUnit.MILLISECONDS)
        Date(System.currentTimeMillis() + time)
    } catch (ex: TimeParser.UnknownUnitException) {
        commandSender.message(FrameworkTranslatableKey.MESSAGE_UNKNOWN_TIME_UNIT, "unit" to ex.unit)
        null
    } catch (ex: TimeParser.InvalidFormatException) {
        commandSender.message(FrameworkTranslatableKey.MESSAGE_UNKNOWN_TIME_FORMAT)
        null
    }

    override fun suggestions(commandSender: CommandSender, start: String): List<String> = emptyList()


}