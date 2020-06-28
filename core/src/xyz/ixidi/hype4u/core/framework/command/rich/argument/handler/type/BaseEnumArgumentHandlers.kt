package xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.type

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.EntityType

class GameModeEnumArgumentHandler : AbstractEnumArgumentHandler<GameMode>(GameMode::class)
class EntityTypeEnumArgumentHandler : AbstractEnumArgumentHandler<EntityType>(EntityType::class)
class MaterialEnumArgumentHandler : AbstractEnumArgumentHandler<Material>(Material::class)