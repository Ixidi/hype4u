package xyz.ixidi.hype4u.core.framework.command.rich.argument.handler

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.primitive.*
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.type.EntityTypeEnumArgumentHandler
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.type.GameModeEnumArgumentHandler
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.type.MaterialEnumArgumentHandler
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.type.PlayerArgumentHandler
import kotlin.reflect.KClass

internal class SimpleArgumentHandlerManager : ArgumentHandlerManager {

    private val list = ArrayList<Triple<KClass<*>, KClass<*>, ArgumentHandler<*>>>()

    init {
        registerHandler(Int::class, IntArgumentHandler::class, IntArgumentHandler())
        registerHandler(Int::class, PositiveIntArgumentHandler::class, PositiveIntArgumentHandler())
        registerHandler(Long::class, LongArgumentHandler::class, LongArgumentHandler())
        registerHandler(Long::class, PositiveLongArgumentHandler::class, PositiveLongArgumentHandler())
        registerHandler(Double::class, DoubleArgumentHandler::class, DoubleArgumentHandler())
        registerHandler(Double::class, PositiveDoubleArgumentHandler::class, PositiveDoubleArgumentHandler())
        registerHandler(String::class, StringArgumentHandler::class, StringArgumentHandler())

        registerHandler(Player::class, PlayerArgumentHandler::class, PlayerArgumentHandler())
        registerHandler(GameMode::class, GameModeEnumArgumentHandler::class, GameModeEnumArgumentHandler())
        registerHandler(EntityType::class, EntityTypeEnumArgumentHandler::class, EntityTypeEnumArgumentHandler())
        registerHandler(Material::class, MaterialEnumArgumentHandler::class, MaterialEnumArgumentHandler())
    }

    override fun <T : Any, H : ArgumentHandler<T>> registerHandler(
        valueClass: KClass<T>,
        handlerClass: KClass<H>,
        handler: ArgumentHandler<T>
    ) {
        list.add(Triple(valueClass, handlerClass, handler))
    }

    override fun getRegisteredHandler(valueClass: KClass<*>, handlerClass: KClass<*>): ArgumentHandler<*>? =
        list.firstOrNull { it.first == valueClass && it.second == handlerClass }?.third

}