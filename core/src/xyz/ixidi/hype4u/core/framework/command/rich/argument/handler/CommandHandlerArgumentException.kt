package xyz.ixidi.hype4u.core.framework.command.rich.argument.handler

class CommandHandlerArgumentException(val errorMessageKey: String, vararg val errorMessageParameters: Pair<String, Any?>) : Exception("That exception should be handled, you should not see that message.")