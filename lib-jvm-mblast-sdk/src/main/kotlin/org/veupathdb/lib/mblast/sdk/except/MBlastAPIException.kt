package org.veupathdb.lib.mblast.sdk.except

/**
 * Represents any exception thrown by the Multi-Blast API SDK itself.
 *
 * Other exceptions may be thrown by underlying libraries depended on by the
 * Multi-Blast API SDK.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class MBlastAPIException : RuntimeException {
  constructor() : super()

  constructor(message: String) : super(message)

  constructor(cause: Throwable) : super(cause)

  constructor(message: String, cause: Throwable) : super(message, cause)
}

