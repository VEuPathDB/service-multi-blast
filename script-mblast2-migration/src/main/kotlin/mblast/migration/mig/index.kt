package mblast.migration.mig

import io.foxcapades.lib.md5.MD5
import mblast.migration.model.Target
import mblast.migration.util.JsonArrayCollector
import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import javax.sql.DataSource

fun RunMigration(dataSource: DataSource) {
  dataSource.connection.use { con ->
    con.autoCommit = false


    _migrateQueryJobs(con)
    con.commit()

    _migrateQueryTargets(con)
    _migrateQueryUserLinks(con)
    _migrateQuerySubJobs(con)


    println("committing")
    con.commit()
  }
}


fun BlastQueryBase.hashWith(query: String, projectID: String, targets: List<Target>) =
  HashID(MD5.hash(HashContainer(this, MD5.hash(query).toHexString(), projectID, targets).toJson().toString()).unsafeBytes)

private data class HashContainer(
  val blastConfig: BlastQueryBase,
  val queryHash:   String,
  val projectID:   String,
  val targets:     List<Target>,
) {
  fun toJson() = Json.newObject {
    put("a", blastConfig.toCliString())
    put("b", queryHash)
    put("c", projectID)
    put("d", targets.stream()
      .map { "${it.name}:${it.file}" }
      .sorted()
      .collect(JsonArrayCollector()))
  }
}

