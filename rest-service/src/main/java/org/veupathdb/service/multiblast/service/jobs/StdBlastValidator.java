package org.veupathdb.service.multiblast.service.jobs;

import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.StdBlastConfig;
import org.veupathdb.service.multiblast.model.blast.ToolOption;

public class StdBlastValidator extends BlastValidator
{
  public ErrorMap validate(StdBlastConfig config) {
    var err = super.validate(config);

    validateDbName(err, config);
    validateSubject(err, config);
    validateSubjectLocation(err, config);
    validateRemote(err, config);
    validateGiList(err, config);
    validateNegativeGiList(err, config);
    validateSeqIdList(err, config);
    validateNegativeSeqIdList(err, config);
    validateTaxIds(err, config);
    validateNegativeTaxIds(err, config);
    validateTaxIdList(err, config);
    validateNegativeTaxIdList(err, config);

    return err;
  }

  static void validateDbName(ErrorMap err, StdBlastConfig conf) {
    BlastValidator.validateDbName(err, conf);

    if (conf.getSubject() != null) {
      err.putError(
        ToolOption.BlastDatabase,
        String.format(ErrIncompatibleWith, ToolOption.SubjectFile)
      );
    }

    if (conf.getSubjectLocation() != null) {
      err.putError(
        ToolOption.BlastDatabase,
        String.format(ErrIncompatibleWith, ToolOption.SubjectLocation)
      );
    }
  }

  static void validateSubject(ErrorMap err, StdBlastConfig conf) {
    if (conf.getSubject() == null)
      return;

    if (conf.getDbName() != null) {
      err.putError(
        ToolOption.SubjectFile,
        String.format(ErrIncompatibleWith, ToolOption.BlastDatabase)
      );
    }
  }

  static void validateSubjectLocation(ErrorMap err, StdBlastConfig conf) {
    if (conf.getSubjectLocation() == null)
      return;

    if (conf.getDbName() != null) {
      err.putError(
        ToolOption.SubjectLocation,
        String.format(ErrIncompatibleWith, ToolOption.BlastDatabase)
      );
    }

    if (conf.getRemote())
      err.putError(
        ToolOption.Remote,
        String.format(ErrIncompatibleWith, ToolOption.Remote)
      );
  }

  static void validateRemote(ErrorMap err, StdBlastConfig conf) {
    if (!conf.getRemote())
      return;

    if (conf.getGiList() != null)
      err.putError(
        ToolOption.Remote,
        String.format(ErrIncompatibleWith, ToolOption.GIListFile)
      );

    if (conf.getSequenceIdList() != null)
      err.putError(
        ToolOption.Remote,
        String.format(ErrIncompatibleWith, ToolOption.SequenceIDListFile)
      );

    if (conf.getTaxIds() != null)
      err.putError(
        ToolOption.Remote,
        String.format(ErrIncompatibleWith, ToolOption.TaxonomyIDs)
      );

    if (conf.getTaxIdList() != null)
      err.putError(
        ToolOption.Remote,
        String.format(ErrIncompatibleWith, ToolOption.TaxonomyIDListFile)
      );

    if (conf.getNegativeGiList() != null)
      err.putError(
        ToolOption.Remote,
        String.format(ErrIncompatibleWith, ToolOption.NegativeGIListFile)
      );

    if (conf.getNegativeSequenceIdList() != null)
      err.putError(
        ToolOption.Remote,
        String.format(ErrIncompatibleWith, ToolOption.NegativeSequenceIDListFile)
      );

    if (conf.getNegativeTaxIds() != null)
      err.putError(
        ToolOption.Remote,
        String.format(ErrIncompatibleWith, ToolOption.NegativeTaxonomyIDs)
      );

    if (conf.getNegativeTaxIdList() != null)
      err.putError(
        ToolOption.Remote,
        String.format(ErrIncompatibleWith, ToolOption.NegativeTaxonomyIDListFile)
      );

    if (conf.getSubjectLocation() != null)
      err.putError(
        ToolOption.Remote,
        String.format(ErrIncompatibleWith, ToolOption.SubjectLocation)
      );

    if (conf.getNumThreads() != null)
      err.putError(
        ToolOption.Remote,
        String.format(ErrIncompatibleWith, ToolOption.NumberOfThreads)
      );
  }

  static void validateGiList(ErrorMap err, StdBlastConfig conf) {/*incompat with remote*/}
  static void validateNegativeGiList(ErrorMap err, StdBlastConfig conf) {/*incompat with remote*/}
  static void validateSeqIdList(ErrorMap err, StdBlastConfig conf) {/*incompat with remote*/}
  static void validateNegativeSeqIdList(ErrorMap err, StdBlastConfig conf) {/*incompat with remote*/}
  static void validateTaxIds(ErrorMap err, StdBlastConfig conf) {/*incompat with remote*/}
  static void validateNegativeTaxIds(ErrorMap err, StdBlastConfig conf) {/*incompat with remote*/}
  static void validateTaxIdList(ErrorMap err, StdBlastConfig conf) {/*incompat with remote*/}
  static void validateNegativeTaxIdList(ErrorMap err, StdBlastConfig conf) {/*incompat with remote*/}
}
