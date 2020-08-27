package xyz.ixidi.hype4u.spigot.core.group

import xyz.ixidi.hype4u.spigot.config.dto.GroupDTO
import kotlin.collections.ArrayList

class GroupManagerImpl: GroupManager {

    class GroupNotRegisteredException(val group: Group) : Exception("Group ${group.name} is not registered.")
    class GroupAlreadyExistsException(val name: String) : Exception("Group $name already exists.")

    private val groupList = ArrayList<Group>()
    private lateinit var defaultGroup: Group

    override fun getAllGroups(): List<Group> = groupList.toList()

    override fun getDefaultGroup(): Group = defaultGroup

    override fun setAsDefaultGroup(group: Group) {
        if (!groupList.contains(group)) {
            throw GroupNotRegisteredException(group)
        }

        defaultGroup = group
    }

    override fun getGroupByName(groupName: String): Group? = groupList.firstOrNull { it.name == groupName }

    override fun createNewGroup(name: String): Group {
        if (getGroupByName(name) != null) {
            throw GroupAlreadyExistsException(name)
        }

        return GroupImpl(name, "{name}", "&7{name}: &f{message}").also { groupList.add(it) }
    }

    fun loadGroupFromDTO(dto: GroupDTO) {
        dto.run {
            if (getGroupByName(name) != null) {
                return
            }

            createNewGroup(name).also {
                it.chatFormat = chatFormat
                it.displayNameFormat = displayNameFormat
                permissions.forEach { (perm, allow) -> it.addPermission(perm, allow) }

                if (isDefault) {
                    setAsDefaultGroup(it)
                }
            }
        }
    }

}