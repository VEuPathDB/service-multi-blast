package org.veupathdb.service.multiblast.service.jobs;

import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.StdBlastConfig;
import org.veupathdb.service.multiblast.model.blast.ToolOption;

public class StdBlastValidator extends BlastValidator
{
  public ErrorMap validate(StdBlastConfig config, boolean ext) {
    var err = super.validate(config, ext);

    validateDbName(err, config, ext);
    validateSubject(err, config, ext);
    validateSubjectLocation(err, config, ext);
    validateRemote(err, config, ext);
    validateGiList(err, config, ext);
    validateNegativeGiList(err, config, ext);
    validateSeqIdList(err, config, ext);
    validateNegativeSeqIdList(err, config, ext);
    validateTaxIds(err, config, ext);
    validateNegativeTaxIds(err, config, ext);
    validateTaxIdList(err, config, ext);
    validateNegativeTaxIdList(err, config, ext);

    return err;
  }

  static void validateDbName(ErrorMap err, StdBlastConfig conf, boolean ext) {
    BlastValidator.validateDbName(err, conf, ext);

    if (conf.getSubject() != null) {
      err.putError(
        Util.key(ToolOption.BlastDatabase, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.SubjectFile, ext))
      );
    }

    if (conf.getSubjectLocation() != null) {
      err.putError(
        Util.key(ToolOption.BlastDatabase, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.SubjectLocation, ext))
      );
    }
  }

  static void validateSubject(ErrorMap err, StdBlastConfig conf, boolean ext) {
    if (conf.getSubject() == null)
      return;

    if (conf.getDbName() != null) {
      err.putError(
        Util.key(ToolOption.SubjectFile, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.BlastDatabase, ext))
      );
    }
  }

  static void validateSubjectLocation(ErrorMap err, StdBlastConfig conf, boolean ext) {
    if (conf.getSubjectLocation() == null)
      return;

    if (conf.getDbName() != null) {
      err.putError(
        Util.key(ToolOption.SubjectLocation, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.BlastDatabase, ext))
      );
    }

    if (conf.getRemote())
      err.putError(
        Util.key(ToolOption.Remote, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.Remote, ext))
      );
  }

  static void validateRemote(ErrorMap err, StdBlastConfig conf, boolean ext) {
    if (!conf.getRemote())
      return;

    if (conf.getGiList() != null)
      err.putError(
        Util.key(ToolOption.Remote, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.GIListFile, ext))
      );

    if (conf.getSequenceIdList() != null)
      err.putError(
        Util.key(ToolOption.Remote, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.SequenceIDListFile, ext))
      );

    if (conf.getTaxIds() != null)
      err.putError(
        Util.key(ToolOption.Remote, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.TaxonomyIDs, ext))
      );

    if (conf.getTaxIdList() != null)
      err.putError(
        Util.key(ToolOption.Remote, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.TaxonomyIDListFile, ext))
      );

    if (conf.getNegativeGiList() != null)
      err.putError(
        Util.key(ToolOption.Remote, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.NegativeGIListFile, ext))
      );

    if (conf.getNegativeSequenceIdList() != null)
      err.putError(
        Util.key(ToolOption.Remote, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.NegativeSequenceIDListFile, ext))
      );

    if (conf.getNegativeTaxIds() != null)
      err.putError(
        Util.key(ToolOption.Remote, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.NegativeTaxonomyIDs, ext))
      );

    if (conf.getNegativeTaxIdList() != null)
      err.putError(
        Util.key(ToolOption.Remote, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.NegativeTaxonomyIDListFile, ext))
      );

    if (conf.getSubjectLocation() != null)
      err.putError(
        Util.key(ToolOption.Remote, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.SubjectLocation, ext))
      );

    if (conf.getNumThreads() != null)
      err.putError(
        Util.key(ToolOption.Remote, ext),
        String.format(ErrIncompatibleWith, Util.key(ToolOption.NumberOfThreads, ext))
      );
  }

  static void validateGiList(ErrorMap err, StdBlastConfig conf, boolean ext) {/*incompat with remote*/}
  static void validateNegativeGiList(ErrorMap err, StdBlastConfig conf, boolean ext) {/*incompat with remote*/}
  static void validateSeqIdList(ErrorMap err, StdBlastConfig conf, boolean ext) {/*incompat with remote*/}
  static void validateNegativeSeqIdList(ErrorMap err, StdBlastConfig conf, boolean ext) {/*incompat with remote*/}
  static void validateTaxIds(ErrorMap err, StdBlastConfig conf, boolean ext) {/*incompat with remote*/}
  static void validateNegativeTaxIds(ErrorMap err, StdBlastConfig conf, boolean ext) {/*incompat with remote*/}
  static void validateTaxIdList(ErrorMap err, StdBlastConfig conf, boolean ext) {/*incompat with remote*/}
  static void validateNegativeTaxIdList(ErrorMap err, StdBlastConfig conf, boolean ext) {/*incompat with remote*/}
}
