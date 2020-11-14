package org.veupathdb.service.multiblast.service.repo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.QueryLocation;
import org.veupathdb.service.multiblast.model.blast.StdBlastConfig;
import org.veupathdb.service.multiblast.model.blast.ToolOption;

abstract class StdBlastSelector<T extends StdBlastConfig> extends BlastSelector<T>
{
  private static final Map<ToolOption, Setter<?>> setters = new HashMap<>();

  static {
    var cls = StdBlastConfig.class;

    try {
      setters.put(
        ToolOption.DatabaseSoftMask,
        new Setter<>(cls.getDeclaredMethod("setDbSoftMask", String.class), Function.identity())
      );
      setters.put(
        ToolOption.DatabaseHardMask,
        new Setter<>(cls.getDeclaredMethod("setDbHardMask", String.class), Function.identity())
      );
      setters.put(
        ToolOption.GIListFile,
        new Setter<>(cls.getDeclaredMethod("setGiList", File.class), File::new)
      );
      setters.put(
        ToolOption.NegativeGIListFile,
        new Setter<>(cls.getDeclaredMethod("setNegativeGiList", File.class), File::new)
      );
      setters.put(
        ToolOption.SequenceIDListFile,
        new Setter<>(cls.getDeclaredMethod("setSequenceIdList", File.class), File::new)
      );
      setters.put(
        ToolOption.NegativeSequenceIDListFile,
        new Setter<>(cls.getDeclaredMethod("setNegativeSequenceIdList", File.class), File::new)
      );
      setters.put(
        ToolOption.TaxonomyIDListFile,
        new Setter<>(cls.getDeclaredMethod("setTaxIdList", File.class), File::new)
      );
      setters.put(
        ToolOption.NegativeTaxonomyIDListFile,
        new Setter<>(cls.getDeclaredMethod("setNegativeTaxIdList", File.class), File::new)
      );
      setters.put(
        ToolOption.SubjectFile,
        new Setter<>(cls.getDeclaredMethod("setSubject", File.class), File::new)
      );
      setters.put(
        ToolOption.SubjectLocation,
        new Setter<>(
          cls.getDeclaredMethod("setSubjectLocation", QueryLocation.class),
          QueryLocation::unsafeFromString
        )
      );
      setters.put(
        ToolOption.WordSize,
        new Setter<>(cls.getDeclaredMethod("setWordSize", Integer.class), Integer::parseInt)
      );
      setters.put(
        ToolOption.TaxonomyIDs,
        new Setter<>(cls.getDeclaredMethod("setTaxIds", String[].class), s -> s.split(","))
      );
      setters.put(
        ToolOption.NegativeTaxonomyIDs,
        new Setter<>(cls.getDeclaredMethod("setNegativeTaxIds", String[].class), s -> s.split(","))
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void parseBlastConfig(BlastConfig out, ToolOption key, String val) throws Exception {
    var set = setters.get(key);

    if (set == null) {
      super.parseBlastConfig(out, key, val);
    } else {
      set.setter.invoke(out, set.converter.apply(val));
    }
  }
}
