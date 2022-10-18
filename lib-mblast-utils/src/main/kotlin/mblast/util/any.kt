package mblast.util

inline fun <T> T?.ifExists(fn: (T) -> Unit) =
  if (this != null) fn(this) else Unit

inline fun <T> T?.ifNotExists(fn: () -> Unit) =
  if (this == null) fn() else Unit