package xyz.ixidi.hype4u.framework.util

import org.koin.core.get
import xyz.ixidi.hype4u.framework.task.TaskManager
import xyz.ixidi.hype4u.framework.injection.FrameworkDependencyInjection

fun <T> asyncWithCallback(run: () -> T, callback: (T) -> Unit, error: (Exception) -> Unit = { it.printStackTrace() }) {
    FrameworkDependencyInjection.get<TaskManager>().run {

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