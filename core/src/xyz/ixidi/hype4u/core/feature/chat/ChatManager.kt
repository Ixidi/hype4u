package xyz.ixidi.hype4u.core.feature.chat

interface ChatManager {

    fun isEnabled(): Boolean
    fun enable()
    fun disable()
    fun swapState()
    fun clear()

}