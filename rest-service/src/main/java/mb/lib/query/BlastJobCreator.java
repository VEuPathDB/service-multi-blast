package mb.lib.query;

import java.time.OffsetDateTime;
import java.util.Optional;

import io.vulpine.lib.iffy.Either;
import mb.api.service.model.ErrorMap;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import mb.lib.query.model.BlastJob;
import mb.lib.query.model.UserBlastRow;

class BlastJobCreator
{
  // See if root job exists and is linked to user
  //   if so, return
  // See if root job exists
  //   if so, link user & return.  Have to be careful as the user may have already
  //   been linked to a sub-job
  // Submit root job to queue
  // Insert root job to db
  // link user to root job
  // for each sub-job
  //

//  private final BlastDBManager db;

}
