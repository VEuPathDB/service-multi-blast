package org.veupathdb.service.mblast.query.service.cache

import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.Const
import org.veupathdb.service.mblast.query.service.MBlastPlatform
import java.io.InputStream

fun GetStdErrForJob(queryJobID: HashID): InputStream {
  val jobInfo = MBlastPlatform.getJob(queryJobID)
    ?: throw NotFoundException()

  if (jobInfo.second?.status?.isFinished != true)
    throw ForbiddenException()

  return MBlastPlatform.openJobFile(queryJobID, Const.StdErrFileName)
    ?: throw NotFoundException()
}