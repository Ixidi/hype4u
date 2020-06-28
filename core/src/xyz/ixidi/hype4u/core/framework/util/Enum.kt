package xyz.ixidi.hype4u.core.framework.util

fun Enum<*>.camelCaseName(): String {
    if (name.length == 1) return name.toLowerCase()

    val stringBuffer = StringBuffer(name.toLowerCase())
    for (i in 1 until stringBuffer.length) {
        if (stringBuffer[i - 1] == '_') {
            stringBuffer.setCharAt(i, stringBuffer[i].toUpperCase())
        }
    }

    return stringBuffer.toString().replace("_", "")
}