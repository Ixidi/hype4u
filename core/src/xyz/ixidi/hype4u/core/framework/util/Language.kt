package xyz.ixidi.hype4u.core.framework.util

import org.koin.core.get
import xyz.ixidi.hype4u.core.internal.injection.DependencyInjection
import xyz.ixidi.hype4u.core.framework.model.data.lang.Language

fun languageString(key: String, vararg parameters: Pair<String, Any?>)
        = DependencyInjection.get<Language>().string(key, *parameters)