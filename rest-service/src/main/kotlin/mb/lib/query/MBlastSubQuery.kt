package mb.lib.query

interface MBlastSubQuery {
  val defLine: String?

  val sequence: String

  fun toStandardString(): String
}
