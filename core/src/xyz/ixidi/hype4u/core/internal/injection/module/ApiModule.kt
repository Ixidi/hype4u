package xyz.ixidi.hype4u.core.internal.injection.module

import org.bukkit.plugin.Plugin
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy
import xyz.ixidi.hype4u.core.framework.command.registration.CommandRegistration
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandlerManager
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.SimpleArgumentHandlerManager
import xyz.ixidi.hype4u.core.framework.gui.GuiManager
import xyz.ixidi.hype4u.core.framework.gui.SimpleGuiManager
import xyz.ixidi.hype4u.core.framework.model.data.YamlMapper
import xyz.ixidi.hype4u.core.internal.data.JacksonYamlMapper
import xyz.ixidi.hype4u.core.internal.data.SimpleLanguage
import xyz.ixidi.hype4u.core.framework.model.data.lang.Language
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import xyz.ixidi.hype4u.core.internal.permission.group.SimpleGroupManager

internal fun apiModule(plugin: Plugin) = module {

    single { plugin }
    singleBy<YamlMapper, JacksonYamlMapper>()
    singleBy<ArgumentHandlerManager, SimpleArgumentHandlerManager>()
    singleBy<Language, SimpleLanguage>()
    singleBy<GuiManager, SimpleGuiManager>()
    single<GroupManager> { SimpleGroupManager(get()) }
    single { CommandRegistration() }

}