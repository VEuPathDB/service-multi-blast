package org.veupathdb.service.multiblast.service.repo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.vulpine.lib.query.util.StatementPreparer;
import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import org.veupathdb.service.multiblast.model.blast.*;

public class SelectBlastnConfig extends StdBlastSelector<BlastnConfig>
{
  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Static Configuration                                             ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private static final Map<ToolOption, Setter<?>> setters = new HashMap<>();

  // Index BlastnConfig specific setter methods.  This is used to dynamically
  // bind arbitrary key/value pairs from the result set to the correct setters
  // in the blast config object.
  static {
    var cls = BlastnConfig.class;

    try {
      setters.put(
        ToolOption.Strand,
        new Setter<>(cls.getDeclaredMethod("setStrand", QueryStrand.class), QueryStrand::fromString)
      );
      setters.put(
        ToolOption.Task,
        new Setter<>(
          cls.getDeclaredMethod("setTask", BlastnTask.class),
          BlastnTask::unsafeFromString
        )
      );
      setters.put(
        ToolOption.GapOpenCost,
        new Setter<>(cls.getDeclaredMethod("setGapOpen", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.GapExtendCost,
        new Setter<>(cls.getDeclaredMethod("setGapExtend", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.MismatchPenalty,
        new Setter<>(cls.getDeclaredMethod("setPenalty", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.MatchReward,
        new Setter<>(cls.getDeclaredMethod("setReward", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.UseMegablastIndex,
        new Setter<>(cls.getDeclaredMethod("setUseIndex", Boolean.class), Boolean::parseBoolean)
      );
      setters.put(
        ToolOption.MegablastIndexName,
        new Setter<>(cls.getDeclaredMethod("setIndexName", String.class), Function.identity())
      );
      setters.put(
        ToolOption.Dust,
        new Setter<>(cls.getDeclaredMethod("setDust", Dust.class), Dust::unsafeFromString)
      );
      setters.put(
        ToolOption.FilteringDatabase,
        new Setter<>(cls.getDeclaredMethod("setFilteringDb", File.class), File::new)
      );
      setters.put(
        ToolOption.WindowMaskerTaxonomicID,
        new Setter<>(
          cls.getDeclaredMethod("setWindowMaskerTaxID", Integer.class),
          Integer::parseInt
        )
      );
      setters.put(
        ToolOption.WindowMaskerDatabase,
        new Setter<>(cls.getDeclaredMethod("setWindowMaskerDB", File.class), File::new)
      );
      setters.put(
        ToolOption.PercentIdentity,
        new Setter<>(cls.getDeclaredMethod("setPercIdentity", Double.class), Double::parseDouble)
      );
      setters.put(
        ToolOption.CullingLimit,
        new Setter<>(cls.getDeclaredMethod("setCullingLimit", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.BestHitOverhang,
        new Setter<>(cls.getDeclaredMethod("setBestHitOverhang", Double.class), Double::parseDouble)
      );
      setters.put(
        ToolOption.BestHitScoreEdge,
        new Setter<>(
          cls.getDeclaredMethod("setBestHitScoreEdge", Double.class),
          Double::parseDouble
        )
      );
      setters.put(
        ToolOption.SubjectBestHit,
        new Setter<>(
          cls.getDeclaredMethod("setSubjectBestHit", Boolean.class),
          Boolean::parseBoolean
        )
      );
      setters.put(
        ToolOption.MegablastTemplateType,
        new Setter<>(
          cls.getDeclaredMethod("setTemplateType", TemplateType.class),
          TemplateType::unsafeFromString
        )
      );
      setters.put(
        ToolOption.MegablastTemplateLength,
        new Setter<>(cls.getDeclaredMethod("setTemplateLength", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.SumStats,
        new Setter<>(cls.getDeclaredMethod("setSumStats", Boolean.class), Boolean::parseBoolean)
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Instance Methods                                                 ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private final int jobId;

  public SelectBlastnConfig(int jobId) {
    this.jobId = jobId;
  }

  public BlastnConfig execute() throws Exception {
    return new BasicPreparedReadQuery<>(
      SQL.Select.Job.ConfigById,
      Util::getPgConnection,
      this::parseBlastConfig,
      StatementPreparer.singleInt(jobId)
    ).execute().getValue();
  }

  @Override
  protected void parseBlastConfig(BlastConfig out, ToolOption key, String val) throws Exception {
    var set = setters.get(key);

    if (set == null)
      super.parseBlastConfig(out, key, val);
    else
      set.setter.invoke(out, set.converter.apply(val));
  }

  @Override
  protected BlastnConfig newBlastConfig() {
    return new BlastnConfig();
  }
}
