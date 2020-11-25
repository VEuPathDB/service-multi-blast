package org.veupathdb.service.multiblast.service.repo;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.HitSorting;
import org.veupathdb.service.multiblast.model.blast.HspSorting;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.impl.LocationImpl;
import org.veupathdb.service.multiblast.model.blast.impl.ReportFormatImpl;
import org.veupathdb.service.multiblast.model.mapping.BlastOptions;

import static org.veupathdb.service.multiblast.model.blast.ToolOption.*;

abstract class BlastSelector<T extends BlastConfig<T>>
{
  protected static final Function<String, Boolean> pBool = (s) -> s == null || Boolean.parseBoolean(s);
  protected static final Function<String, Integer> pInt  = Integer::parseInt;
  protected static final Function<String, Byte>    pByte = Byte::parseByte;
  protected static final Function<String, Double>  pDub  = Double::parseDouble;
  protected static final Function<String, File>    pFile = File::new;
  protected static final Function<String, String>  pStr  = Function.identity();

  private static final Map<ToolOption, Setter<BlastConfig<?>, ?>> setters = new HashMap<>()
  {{
    put(Help, new Setter<>(BlastConfig::enableHelp, pBool));
    put(Version, new Setter<>(BlastConfig::enableVersion, pBool));
    put(Query, new Setter<>(BlastConfig::setQueryFile, pFile));
    put(QueryLocation, new Setter<>(BlastConfig::setQueryLocation, LocationImpl::fromString));
    put(BlastDatabase, new Setter<>(BlastConfig::setDatabase, Path::of));
    put(OutputFile, new Setter<>(BlastConfig::setOutputFile, pFile));
    put(ExpectationValue, new Setter<>(BlastConfig::setExpectValue, BigDecimal::new));
    put(OutputFormat, new Setter<>(BlastConfig::setReportFormat, ReportFormatImpl::fromString));
    put(ShowNCBIGIs, new Setter<>(BlastConfig::enableNCBIGenInfoIds, pBool));
    put(NumDescriptions, new Setter<>(BlastConfig::setNumDescriptions, pInt));
    put(NumAlignments, new Setter<>(BlastConfig::setNumAlignments, pInt));
    put(LineLength, new Setter<>(BlastConfig::setLineLength, pInt));
    put(HTMLOutput, new Setter<>(BlastConfig::enableHtmlOutput, pBool));
    put(SortHits, new Setter<>(BlastConfig::setHitSorting, HitSorting::fromString));
    put(SortHSPs, new Setter<>(BlastConfig::setHspSorting, HspSorting::fromString));
    put(LowercaseMasking, new Setter<>(BlastConfig::enableLowercaseMasking, pBool));
    put(QueryCoveragePercentHSP, new Setter<>(BlastConfig::setQueryCoverageHspPercent, pDub));
    put(MaxHSPs, new Setter<>(BlastConfig::setMaxHsps, pInt));
    put(MaxTargetSequences, new Setter<>(BlastConfig::setMaxTargetSequences, pInt));
    put(DatabaseEffectiveSize, new Setter<>(BlastConfig::setEffectiveDatabaseSize, pByte));
    put(
      SearchSpaceEffectiveLength,
      new Setter<>(BlastConfig::setEffectiveSearchSpaceLength, pByte)
    );
    put(ImportSearchStrategy, new Setter<>(BlastConfig::setSearchStrategyImportFile, pFile));
    put(ExportSearchStrategy, new Setter<>(BlastConfig::setSearchStrategyExportFile, pFile));
    put(XDropoffUngappedExtensions, new Setter<>(BlastConfig::setExtensionDropoffUngapped, pDub));
    put(ParseDefLines, new Setter<>(BlastConfig::enableDefLineParsing, pBool));
    put(NumberOfThreads, new Setter<>(BlastConfig::setThreadCount, pByte));
    put(Remote, new Setter<>(BlastConfig::enableRemoteSearchExecution, pBool));
    put(EntrezQuery, new Setter<>(BlastConfig::setEntrezQuery, pStr));
    put(SoftMasking, new Setter<>(BlastConfig::enableSoftMasking, pBool));
    put(MultiHitWindowSize, new Setter<>(BlastConfig::setMultiHitWindowSize, pInt));
  }};

  protected static int[] stringToIntArray(String value) {
    return Arrays.stream(value.split(","))
      .mapToInt(Integer::parseInt)
      .toArray();
  }

  protected abstract T newBlastConfig();

  protected T parseBlastConfig(ResultSet rs) throws Exception {
    var out = newBlastConfig();

    while (rs.next()) {
      parseBlastConfig(
        out,
        BlastOptions.getInstance()
          .requireValue(rs.getShort(Column.Job.Config.OptionID)),
        rs.getString(Column.Job.Config.Value)
      );
    }

    return out;
  }

  protected void parseBlastConfig(T out, ToolOption key, String value) {
    var set = setters.get(key);

    if (set != null)
      set.set(out, value);
  }

  static class Setter<P extends BlastConfig<?>, T>
  {
    private final BiConsumer<P, T>    setter;
    private final Function<String, T> converter;

    Setter(BiConsumer<P, T> setter, Function<String, T> converter) {
      this.setter    = setter;
      this.converter = converter;
    }

    void set(P out, String value) {
      setter.accept(out, converter.apply(value));
    }
  }
}
