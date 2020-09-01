package xyz.ixidi.hype4u.core.repository.userinfo

import java.util.*

interface UserInfoRepository {

    fun loadUserInfo(uuid: UUID): UserInfo?
    fun loadUserInfo(name: String): UserInfo?

}