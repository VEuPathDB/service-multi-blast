package org.veupathdb.service.mblast.query.sql.queries

import org.intellij.lang.annotations.Language
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection

@Language("Oracle")
private const val SQL = """
INSERT INTO
  ${Schema.MBlast}.${Table.QueryToQueries} (
    ${Column.ParentJobID}
  , ${Column.ChildJobID}
  , ${Column.Position}
  )
VALUES
 (?, ?, ?)
"""

fun Connection.insertQueryToSubqueryLink(parentJobID: HashID, childJobID: HashID, position: UInt) {
  prepareStatement(SQL).use {
    it.setString(1, parentJobID.string)
    it.setString(2, childJobID.string)
    it.setInt(3, position.toInt())
    it.execute()
  }
}