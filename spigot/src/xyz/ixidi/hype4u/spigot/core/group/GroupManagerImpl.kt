package xyz.ixidi.hype4u.spigot.core.group

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class GroupManagerImpl: GroupManager {

    class DuplicatedNameException(val name: String) : Exception("Duplicated name $name.")
    class DuplicatedDefaultException : Exception("There are two or more groups set to default.")
    class NotDefaultException : Exception("There is not default group.")
    class GroupNotRegisteredException(val group: Group) : Exception("Group ${group.name} is not registered.")

    private val groupList = ArrayList<Group>()
    private lateinit var defaultGroup: Group

    private val userGroupMap = HashMap<UUID, Pair<Group, Boolean>>()

    override fun getAllGroups(): List<Group> = groupList.toList()

    override fun getDefaultGroup(): Group = defaultGroup

    override fun setAsDefaultGroup(group: Group) {
        if (!groupList.contains(group)) {
            throw GroupNotRegisteredException(group)
        }

        defaultGroup = group
    }

    override fun isPrimaryGroup(uuid: UUID, group: Group): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAsPrimaryGroup(uuid: UUID, group: Group) {
        TODO("Not yet implemented")
    }

    override fun getGroupByName(groupName: String): Group? {
        TODO("Not yet implemented")
    }

    override fun getPrimaryGroupByUUID(uuid: UUID): Group? {
        TODO("Not yet implemented")
    }

    override fun getSecondaryGroupsByUUUI(uuid: UUID): List<Group> {
        TODO("Not yet implemented")
    }

    override fun getAllGroupsByUUUI(uuid: UUID): List<Group> {
        TODO("Not yet implemented")
    }

    override fun createNewGroup(name: String): Group {
        TODO("Not yet implemented")
    }

}