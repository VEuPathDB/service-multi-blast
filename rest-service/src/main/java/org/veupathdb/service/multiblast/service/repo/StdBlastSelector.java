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
        ToolOption.GIList,
        new Setter<>(cls.getDeclaredMethod("setGiList", File.class), File::new)
      );
      setters.put(
        ToolOption.NegativeGIList,
        new Setter<>(cls.getDeclaredMethod("setNegativeGiList", File.class), File::new)
      );
      setters.put(
        ToolOption.SequenceIDList,
        new Setter<>(cls.getDeclaredMethod("setSequenceIdList", File.class), File::new)
      );
      setters.put(
        ToolOption.NegativeSequenceIDList,
        new Setter<>(cls.getDeclaredMethod("setNegativeSequenceIdList", File.class), File::new)
      );
      setters.put(
        ToolOption.TaxonomyIDList,
        new Setter<>(cls.getDeclaredMethod("setTaxIdList", File.class), File::new)
      );
      setters.put(
        ToolOption.NegativeTaxonomyIDList,
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
          QueryLocation::fromString
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
