package mb.lib.util

internal inline fun <T> T.then(fn: (T) -> Unit): Unit = fn(this)
