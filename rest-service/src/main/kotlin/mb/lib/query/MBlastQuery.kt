package mb.lib.query

interface MBlastQuery {
  val sequences: List<MBlastSubQuery>

  fun getFullQuery(): String
}
