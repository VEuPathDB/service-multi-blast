package org.veupathdb.service.multiblast.service.repo;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.veupathdb.service.multiblast.model.blast.*;
import org.veupathdb.service.multiblast.model.mapping.BlastOptions;

abstract class BlastSelector<T extends BlastConfig>
{
  private static final Map<ToolOption, Setter<?>> setters = new HashMap<>();

  static {
    var cls = BlastConfig.class;

    try {
      setters.put(
        ToolOption.BlastDatabase,
        new Setter<>(cls.getDeclaredMethod("setBlastDatabase", String.class), Function.identity())
      );
      setters.put(
        ToolOption.DatabaseEffectiveSize,
        new Setter<>(cls.getDeclaredMethod("setDbSize", Byte.class), Byte::parseByte)
      );
      setters.put(
        ToolOption.EntrezQuery,
        new Setter<>(cls.getDeclaredMethod("setEntrezQuery", String.class), Function.identity())
      );
      setters.put(
        ToolOption.ExpectationValue,
        new Setter<>(cls.getDeclaredMethod("setExpectValue", Double.class), Double::parseDouble)
      );
      setters.put(
        ToolOption.ExportSearchStrategy,
        new Setter<>(cls.getDeclaredMethod("setExportSearchStrategy", File.class), File::new)
      );
      setters.put(
        ToolOption.HTMLOutput,
        new Setter<>(cls.getDeclaredMethod("setHtmlOutputEnabled", Boolean.class), Boolean::parseBoolean)
      );
      setters.put(
        ToolOption.ImportSearchStrategy,
        new Setter<>(cls.getDeclaredMethod("setImportSearchStrategy", File.class), File::new)
      );
      setters.put(
        ToolOption.LineLength,
        new Setter<>(cls.getDeclaredMethod("setImportSearchStrategy", File.class), File::new)
      );
      setters.put(
        ToolOption.LowercaseMasking,
        new Setter<>(
          cls.getDeclaredMethod("setLowercaseMaskingEnabled", Boolean.class),
          Boolean::parseBoolean
        )
      );
      setters.put(
        ToolOption.MaxHSPs,
        new Setter<>(cls.getDeclaredMethod("setMaxHSPs", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.MaxTargetSequences,
        new Setter<>(
          cls.getDeclaredMethod("setMaxTargetSequences", Integer.class),
          Integer::parseInt
        )
      );
      setters.put(
        ToolOption.NumDescriptions,
        new Setter<>(cls.getDeclaredMethod("setNumDescriptions", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.NumAlignments,
        new Setter<>(cls.getDeclaredMethod("setNumAlignments", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.NumberOfThreads,
        new Setter<>(cls.getDeclaredMethod("setNumThreads", Byte.class), Byte::parseByte)
      );
      setters.put(
        ToolOption.OutputFile,
        new Setter<>(cls.getDeclaredMethod("setOut", File.class), File::new)
      );
      setters.put(
        ToolOption.Query,
        new Setter<>(cls.getDeclaredMethod("setQuery", File.class), File::new)
      );
      setters.put(
        ToolOption.QueryLocation,
        new Setter<>(
          cls.getDeclaredMethod("setQueryLoc", QueryLocation.class),
          QueryLocation::unsafeFromString
        )
      );
      setters.put(
        ToolOption.Remote,
        new Setter<>(cls.getDeclaredMethod("setRemoteEnabled", Boolean.class), Boolean::parseBoolean)
      );
      setters.put(
        ToolOption.SearchSpaceEffectiveLength,
        new Setter<>(cls.getDeclaredMethod("setSearchSpace", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.ShowNCBIGIs,
        new Setter<>(cls.getDeclaredMethod("setShowGIsEnabled", Boolean.class), Boolean::parseBoolean)
      );
      setters.put(
        ToolOption.SoftMasking,
        new Setter<>(cls.getDeclaredMethod("setSoftMasking", Boolean.class), Boolean::parseBoolean)
      );
      setters.put(
        ToolOption.SortHits,
        new Setter<>(cls.getDeclaredMethod("setSortHits", HitSorting.class), HitSorting::fromString)
      );
      setters.put(
        ToolOption.SortHSPs,
        new Setter<>(cls.getDeclaredMethod("setSortHsps", HspSorting.class), HspSorting::fromString)
      );
      setters.put(
        ToolOption.MultiHitWindowSize,
        new Setter<>(cls.getDeclaredMethod("setWindowSize", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.Version,
        new Setter<>(cls.getDeclaredMethod("setVersionEnabled", Boolean.class), Boolean::parseBoolean)
      );
      setters.put(
        ToolOption.XDropoffUngappedExtensions,
        new Setter<>(cls.getDeclaredMethod("setxDropUngap", Double.class), Double::parseDouble)
      );
      setters.put(
        ToolOption.OutputFormat,
        new Setter<>(cls.getDeclaredMethod("setOutFormat", OutFormat.class), OutFormat::fromString)
      );
      setters.put(
        ToolOption.QueryCoveragePercentHSP,
        new Setter<>(
          cls.getDeclaredMethod("setQueryCoveragePercentHSP", Double.class),
          Double::parseDouble
        )
      );
      setters.put(
        ToolOption.ParseDefLines,
        new Setter<>(
          cls.getDeclaredMethod("setParseDefLinesEnabled", Boolean.class),
          Boolean::parseBoolean
        )
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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

  protected void parseBlastConfig(BlastConfig out, ToolOption key, String value) throws Exception {
    var set = setters.get(key);

    if (set != null) {
      set.setter.invoke(out, set.converter.apply(value));
    }
  }


  static class Setter<T>
  {
    final Method              setter;
    final Function<String, T> converter;

    Setter(Method setter, Function<String, T> converter) {
      this.setter    = setter;
      this.converter = converter;
    }
  }
}
