package org.veupathdb.service.mblast.query.service.jobs.delete

import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.sql.JobDBManager

/**
 * Deletes the link between a user and a BLAST job.
 *
 * If the link doesn't actually exist, this method throws a 404 exception.
 *
 * @param queryJobID ID of the job the user should be unlinked from.
 *
 * @param userID ID of the user to unlink.
 *
 * @throws NotFoundException If the target job does not exist, or the user
 * represented by the given user ID was not linked to the target job.
 */
fun DeleteUserJobLink(queryJobID: HashID, userID: Long) {
  // Lookup the job from the user's collection or throw a 404
  JobDBManager.getFullUserJob(queryJobID, userID) ?: throw NotFoundException()
  // Delete the user link`
  JobDBManager.deleteUserLink(queryJobID, userID)
}
