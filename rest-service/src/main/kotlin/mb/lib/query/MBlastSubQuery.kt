package mb.lib.query

import java.io.InputStream

interface MBlastSubQuery {
  val defLine: String?

  val sequence: String

  fun toStandardString(): InputStream
}
