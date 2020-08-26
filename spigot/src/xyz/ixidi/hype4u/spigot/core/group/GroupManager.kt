package xyz.ixidi.hype4u.spigot.core.group

import org.bukkit.entity.Player
import java.util.*

interface GroupManager {

    fun getAllGroups(): List<Group>

    fun getDefaultGroup(): Group
    fun setAsDefaultGroup(group: Group)

    fun isPrimaryGroup(uuid: UUID, group: Group): Boolean
    fun isPrimaryGroup(player: Player, group: Group) = isPrimaryGroup(player.uniqueId, group)

    fun setAsPrimaryGroup(uuid: UUID, group: Group)
    fun setAsPrimaryGroup(player: Player, group: Group) {
        setAsPrimaryGroup(player.uniqueId, group)
    }

    fun getGroupByName(groupName: String): Group?

    fun getPrimaryGroupByUUID(uuid: UUID): Group?
    fun getPrimaryGroupByPlayer(player: Player): Group? = getPrimaryGroupByUUID(player.uniqueId)

    fun getSecondaryGroupsByUUUI(uuid: UUID): List<Group>
    fun getSecondaryGroupsByPlayer(player: Player): List<Group> = getSecondaryGroupsByUUUI(player.uniqueId)

    fun getAllGroupsByUUUI(uuid: UUID): List<Group>
    fun getAllGroupsByPlayer(player: Player): List<Group> = getAllGroupsByUUUI(player.uniqueId)

    fun createNewGroup(name: String): Group

}