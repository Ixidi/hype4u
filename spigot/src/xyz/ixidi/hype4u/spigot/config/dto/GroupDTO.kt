package xyz.ixidi.hype4u.spigot.config.dto

data class GroupDTO(
    val name: String,
    val isDefault: Boolean,
    val displayNameFormat: String,
    val chatFormat: String,
    val permissions: Map<String, Boolean>
)