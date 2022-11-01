package org.veupathdb.lib.mblast.sdk.util

data class Either<E, V>(val error: E? = null, val value: V? = null) {
  val isError get() = error != null
  val isValue get() = value != null

  init {
    if ((error != null) == (value != null))
      throw IllegalArgumentException("An either cannot be constructed with two null or two non-null values.")
  }
}