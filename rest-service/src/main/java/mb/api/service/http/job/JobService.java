package mb.api.service.http.job;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.ws.rs.NotFoundException;

import mb.api.model.*;
import mb.api.model.io.JsonKeys;
import mb.api.service.model.ErrorMap;
import mb.lib.blast.model.BlastQuery;
import mb.lib.config.Config;
import mb.lib.model.HashID;
import mb.lib.query.BlastManager;
import mb.lib.query.model.BlastJob;
import mb.lib.util.BlastConv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException;
import org.veupathdb.lib.container.jaxrs.model.User;

import static mb.api.service.http.Util.wrapException;

public class JobService
{
  private static final Logger Log  = LogManager.getLogger(JobService.class);
  private static final Config Conf = Config.getInstance();

  private static JobService instance;

  private JobService() {
    Log.trace("::new()");
  }

  public static JobService getInstance() {
    Log.trace("::getInstance()");

    if (instance == null)
      instance = new JobService();

    return instance;
  }

  public IOLongJobResponse getJob(HashID jobID, long userID) {
    Log.trace("#getJob(jobID={}, userID={})", jobID, userID);

    try {
      return Util.translateLongResponse(BlastManager.getAndLinkUserBlastJob(jobID, userID)
        .orElseThrow(NotFoundException::new));
    } catch (Exception e) {
      throw wrapException(e);
    }
  }

  public List<IOShortJobResponse> getShortJobList(long userID) {
    Log.trace("#getJobs(userID={})", userID);

    try {
      return Util.translateShortResponses(BlastManager.getUserBlastJobs(userID));
    } catch (Exception ex) {
      throw wrapException(ex);
    }
  }

  public String getQuery(HashID jobID) {
    Log.trace("#getQuery(jobID={})", jobID);

    try {
      return BlastManager.getJobQuery(jobID).orElseThrow(NotFoundException::new);
    } catch (Exception ex) {
      throw wrapException(ex);
    }
  }

  private static final String ErrTooManySeqs  = "Too many sequences in input query.  Queries can have at most %d sequences.";
  private static final String ErrQueryInvalid = "Invalid character \"%s\" at position %d in sequence %d on line %d.";

  public IOJobPostResponse createJob(IOJsonJobRequest input, long userID) {
    Log.trace("#createJob(input={}, userID={})", input, userID);

    try {
      var rawQuery = input.getConfig().getQuery();

      // We are abusing the config query file field, null it out so it doesn't
      // get stored or sent anywhere with the query in this field.
      input.getConfig().setQuery(null);

      if (rawQuery == null)
        throw new UnprocessableEntityException(new ErrorMap(JsonKeys.Query, "Query is required."));

      rawQuery = rawQuery.trim();

      if (rawQuery.length() > Conf.getMaxInputQuerySize())
        throw new UnprocessableEntityException(new ErrorMap(JsonKeys.Query, "Query is too large."));

      var query = BlastQuery.parse(input.getConfig().getTool(), rawQuery);

      // Limit input sequence count
      if (query.getSequenceCount() > Conf.getMaxSeqsPerQuery())
        throw new UnprocessableEntityException(new ErrorMap(
          JsonKeys.Query,
          String.format(ErrTooManySeqs, Conf.getMaxSeqsPerQuery())
        ));

      {
        var validationResult = query.validate();

        if (validationResult != null) {
          throw new UnprocessableEntityException(new ErrorMap(
            JsonKeys.Query,
            String.format(
              ErrQueryInvalid,
              validationResult.getCharacter(),
              validationResult.getPosition(),
              validationResult.getSequence(),
              validationResult.getLine()
            )
          ));
        }
      }

      JobUtil.verifyResultLimit(input, (int) rawQuery.codePoints()
        .filter(c -> c == '>')
        .count());

      if (input.getTargets() == null || input.getTargets().size() == 0)
        throw new UnprocessableEntityException(new ErrorMap(
          "targets",
          "1 or more targets must be selected."
        ));

      var conv = BlastConv.convert(input.getConfig());
      BlastManager.validateConfig(conv)
        .ifPresent(e -> {throw new UnprocessableEntityException(e);});

      var dbPath = JobUtil.makeDBPaths(input.getSite(), input.getTargets());
      conv.setDBFile(dbPath);

      var res = BlastManager.submitJob(new BlastJob()
        .setConfig(conv)
        .setQuery(query)
        .setSite(input.getSite())
        .setDescription(input.getDescription())
        .setUserID(userID)
        .setMaxDLSize(input.getMaxResultSize())
        .setTargets(BlastConv.convert(input.getTargets()))
        .setPrimary(input.getIsPrimary())
      );

      return new IOJobPostResponseImpl().setJobId(res.getJobID().string());
    } catch (Exception ex) {
      throw wrapException(ex);
    }
  }

  public IOJobPostResponse createJob(InputStream query, IOJsonJobRequest props, User user) {
    Log.trace("#createJob(query={}, props={}, user={})", query, props, user.getUserID());

    try (query) {
      if (query == null)
        return createJob(props, user.getUserID());

      props.getConfig().setQuery(new String(query.readAllBytes(), StandardCharsets.UTF_8));

      return createJob(props, user.getUserID());
    } catch (Exception e) {
      throw wrapException(e);
    }
  }

  public void rerunJob(HashID jobID, long userID) {
    Log.trace("#rerunJob(jobID={}, userID={})", jobID, userID);

    try {
      BlastManager.rerunJob(jobID, userID);
    } catch (Exception e) {
      throw wrapException(e);
    }
  }
}
