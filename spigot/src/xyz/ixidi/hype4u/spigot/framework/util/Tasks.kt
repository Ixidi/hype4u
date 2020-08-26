package xyz.ixidi.hype4u.spigot.framework.util

import org.koin.core.get
import xyz.ixidi.hype4u.spigot.framework.task.TaskManager
import xyz.ixidi.hype4u.spigot.injection.DependencyInjection

fun <T> asyncWithCallback(run: () -> T, callback: (T) -> Unit, error: (Exception) -> Unit = { it.printStackTrace() }) {
    DependencyInjection.get<TaskManager>().run {

        runAsyncTask {
            val t = try {
                run()
            } catch (ex: Exception) {
                runSyncTask { error(ex) }
                return@runAsyncTask
            }

            runSyncTask { callback(t) }
        }

    }
}