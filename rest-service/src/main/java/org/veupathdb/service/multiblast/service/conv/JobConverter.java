package org.veupathdb.service.multiblast.service.conv;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.ws.rs.InternalServerErrorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.model.blast.*;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.model.internal.JobStatus;

public class JobConverter
{
  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Class Instance Management                                        ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private static JobConverter instance;

  private final Logger log;

  private JobConverter() {
    log = LogManager.getLogger(getClass());
  }

  public JobConverter getInstance() {
    if (instance == null)
      return instance = new JobConverter();

    return instance;
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Conversion Methods                                               ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  public IOJobStatus fromInternal(JobStatus val) {
    return switch(val) {
      case Queued -> IOJobStatus.QUEUED;
      case InProgress -> IOJobStatus.INPROGRESS;
      case Errored -> IOJobStatus.ERRORED;
      case Completed -> IOJobStatus.COMPLETED;
    };
  }

  public JobStatus toInternal(IOJobStatus val) {
    return switch(val) {
      case QUEUED -> JobStatus.Queued;
      case INPROGRESS -> JobStatus.InProgress;
      case ERRORED -> JobStatus.Errored;
      case COMPLETED -> JobStatus.Completed;
    };
  }

  public GetJobResponse fromInternal(Job job) {
    var out = new GetJobResponseImpl();
    out.setId(job.getJobId());
    out.setStatus(fromInternal(job.getStatus()));
    out.setConfig(BlastConverter.);
  }


  void _fromInternal(Object obj, Class<?> clz, StdBlastConfig conf) throws Exception {



  }

  void _fromInternal(Object obj, Map<String, Method> methods, BlastConfig conf) throws Exception {
//    try {
    // TODO: What should the query return?  Should there be a flag indicating
    //       whether the query was a file or text input?  Should the raw text
    //       be returned no matter what?  Should the query be available via
    //       a separate endpoint (with the query value here being a url)?
    methods.get("setQuery").invoke(obj, conf.getQuery().toString());
    methods.get("setQueryLoc").invoke(obj, fromInternal(conf.getQueryLoc()));
    methods.get("setEValue").invoke(obj, conf.getExpectValue());
    methods.get("setLineLength").invoke(obj, conf.getLineLength());
    methods.get("setSortHits").invoke(obj, fromInternal(conf.getSortHits()));
    methods.get("setSortHSPs").invoke(obj, fromInternal(conf.getSortHsps()));
    methods.get("setQCovHSPPerc").invoke(obj, conf.getQueryCoveragePercentHSP());
    methods.get("setNumDescriptions").invoke(obj, conf.getNumDescriptions());
    methods.get("setNumAlignments").invoke(obj, conf.getNumAlignments());
    methods.get("setMaxTargetSeqs").invoke(obj, conf.getMaxTargetSequences());
    methods.get("setMaxHSPs").invoke(obj, conf.getMaxHSPs());
    methods.get("setMaxHSPs").invoke(obj, conf.getMaxHSPs());
    methods.get("setDbSize").invoke(obj, conf.getDbSize());
    methods.get("setSearchSpace").invoke(obj, conf.getSearchSpace());
    methods.get("setParseDefLines").invoke(obj, conf.isParseDefLinesEnabled());
    methods.get("setOutFmt").invoke(obj, fromInternal(conf.getOutFormat()));
    methods.get("setSoftMasking").invoke(obj, conf.getSoftMasking());
    methods.get("setLcaseMasking").invoke(obj, conf.isLowercaseMaskingEnabled());
    methods.get("setXdropUngap").invoke(obj, conf.getXDropUngap());
    methods.get("setWindowSize()").invoke(obj, conf.getWindowSize());


//    } catch (Exception e) {
//      if (e instanceof RuntimeException)
//        throw (RuntimeException) e;
//      throw new RuntimeException(e);
//    }
  }

}
