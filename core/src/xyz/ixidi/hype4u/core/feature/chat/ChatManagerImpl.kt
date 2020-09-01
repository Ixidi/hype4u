package xyz.ixidi.hype4u.core.feature.chat

import org.bukkit.Server
import xyz.ixidi.hype4u.core.misc.Permission

class ChatManagerImpl(
    private val server: Server
) : ChatManager {

    private companion object val CLEAR_MESSAGE = String(CharArray(8192) { ' ' })

    class ChatAlreadyEnabledException: Exception()
    class ChatAlreadyDisabledException: Exception()

    private var enabled = true

    override fun isEnabled(): Boolean = enabled

    override fun enable() {
        if (enabled) throw ChatAlreadyEnabledException()
        enabled = true
    }

    override fun disable() {
        if (!enabled) throw ChatAlreadyDisabledException()
        enabled = false
    }

    override fun swapState() {
        enabled = !enabled
    }

    override fun clear() {
        server.onlinePlayers
            .filter { !it.hasPermission(Permission.CHAT_CLEAR_BYPASS) }
            .forEach { it.sendMessage(CLEAR_MESSAGE) }
    }

}