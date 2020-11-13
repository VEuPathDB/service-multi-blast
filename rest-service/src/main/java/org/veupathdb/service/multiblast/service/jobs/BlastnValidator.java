package org.veupathdb.service.multiblast.service.jobs;

import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.BlastnConfig;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

public class BlastnValidator extends BlastValidator
{
  private static BlastnValidator instance;

  public ErrorMap validateConfig(BlastnConfig config, boolean ext) {
    var errors = super.validate(config, ext);

    if (config.getWordSize() < 4)
      errors.putError(JsonKeys.WORD_SIZE, "word size must be 4 or greater");

    if (config.getPenalty() > 0)
      errors.putError(JsonKeys.PENALTY, "penalty cannot be grater than 0");

    if (config.getReward() < 0)
      errors.putError(JsonKeys.REWARD, "reward cannot be less than 0");

    if (config.getSubject() != null && forbidSubject(config))
      errors.putError(JsonKeys.SUBJECT, Err.SubjectCompatibility);

    if (config.getSubjectLocation() != null && forbidSubject(config))
      errors.putError(JsonKeys.SUBJECT, Err.SubjectCompatibility);

    if (config.getNumDescriptions() != null) {
      if (config.getNumDescriptions() < 0)
        errors.putError(JsonKeys.NUM_DESCRIPTIONS, Err.LessThanZero);

      if (config.getOutFormat().hasFormat() && config.getOutFormat().getFormat().getValue() > 4)
        errors.putError(JsonKeys.NUM_DESCRIPTIONS, Err.OnlyForFmtLte4);

      if (config.getMaxTargetSequences() != null)
        errors.putError(JsonKeys.NUM_DESCRIPTIONS, Err.ForbidWithMaxTargetSeqs);
    }

    if (config.getNumAlignments() != null) {
      if (config.getNumAlignments() < 0)
        errors.putError(JsonKeys.NUM_ALIGNMENTS, Err.LessThanZero);

      if (config.getMaxTargetSequences() != null)
        errors.putError(JsonKeys.NUM_ALIGNMENTS, Err.ForbidWithMaxTargetSeqs);
    }

    if (config.getSortHits() != null
      && config.getOutFormat().hasFormat()
      && config.getOutFormat().getFormat().getValue() > 4)
      errors.putError(JsonKeys.SORT_HITS, Err.OnlyForFmtLte4);

    if (config.getSortHsps() != null
      && config.getOutFormat().hasFormat()
      && config.getOutFormat().getFormat().getValue() > 1)
      errors.putError(JsonKeys.SORT_HSPS, Err.OnlyForPairwise);

    if (config.getGiList() != null && forbidGiList(config))
      errors.putError(JsonKeys.GI_LIST, Err.ForbidGiList);

    if (config.getSequenceIdList() != null && forbidSeqIdList(config))
      errors.putError(JsonKeys.SEQ_ID_LIST, Err.ForbidSeqIdList);

    if (config.getNegativeGiList() != null && forbidNegGiList(config))
      errors.putError(JsonKeys.NEGATIVE_GI_LIST, Err.ForbidNegativeGiList);

    if (config.getNegativeSequenceIdList() != null && forbidNegSeqIdList(config))
      errors.putError(JsonKeys.NEGATIVE_SEQ_ID_LIST, Err.ForbidNegativeSeqIdList);

    if (config.getTaxIds() != null && forbidTaxIds(config))
      errors.putError(JsonKeys.TAX_IDS, Err.ForbidTaxIds);

    if (config.getNegativeTaxIds() != null && forbidNegativeTaxIds(config))
      errors.putError(JsonKeys.TAX_IDS, Err.ForbidNegativeTaxIds);

    if (config.getTaxIdList() != null && forbidTaxIdList(config))
      errors.putError(JsonKeys.TAX_IDS, Err.ForbidTaxIdList);

    if (config.getNegativeTaxIdList() != null && forbidNegativeTaxIdList(config))
      errors.putError(JsonKeys.TAX_IDS, Err.ForbidNegativeTaxIdList);

    // The remote field must be present and set to "true" to use the entrezQuery
    // field.
    //
    // You may ask something like "why not just default it to true when using
    // this field?"  The remote flag is significant and requiring it forces the
    // client to acknowledge that they actually want to run a remote query.
    if (config.getEntrezQuery() != null && config.isRemoteEnabled())
      errors.putError(OptionName.REMOTE, Err.RequireRemote);

//    if (config.getDbSoftMask() != null && forbidDbSoftMask())
//      errors.putError(OptionName.DB_SOFT_MASK, Err.ForbidDbSoftMask);
//
//    if (config.getDbHardMask() != null && forbidDbHardMask())
//      errors.putError(OptionName.DB_HARD_MASK, Err.ForbidDbHardMask);

    return null;
  }

  public static BlastnValidator getInstance() {
    if (instance == null)
      instance = new BlastnValidator();

    return instance;
  }

  public static ErrorMap validate(BlastnConfig config) {
    return getInstance().validateConfig(config);
  }

  static boolean forbidGiList(BlastnConfig config) {
    return config.isRemoteEnabled() || forbidList(
      config.getSequenceIdList(),
      config.getTaxIds(),
      config.getTaxIdList(),
      config.getNegativeGiList(),
      config.getNegativeSequenceIdList(),
      config.getNegativeTaxIds(),
      config.getNegativeTaxIdList(),
      config.getSubject(),
      config.getSubjectLocation()
    );
  }

  static boolean forbidSeqIdList(BlastnConfig config) {
    return config.isRemoteEnabled() || forbidList(
      config.getGiList(),
      config.getTaxIds(),
      config.getTaxIdList(),
      config.getNegativeGiList(),
      config.getNegativeSequenceIdList(),
      config.getNegativeTaxIds(),
      config.getNegativeTaxIdList(),
      config.getSubject(),
      config.getSubjectLocation()
    );
  }

  static boolean forbidNegGiList(BlastnConfig config) {
    return config.isRemoteEnabled() || forbidList(
      config.getGiList(),
      config.getSequenceIdList(),
      config.getTaxIds(),
      config.getTaxIdList(),
      config.getNegativeSequenceIdList(),
      config.getNegativeTaxIds(),
      config.getNegativeTaxIdList(),
      config.getSubject(),
      config.getSubjectLocation()
    );
  }

  static boolean forbidTaxIds(BlastnConfig config) {
    return config.isRemoteEnabled() || forbidList(
      config.getGiList(),
      config.getSequenceIdList(),
      config.getTaxIdList(),
      config.getNegativeSequenceIdList(),
      config.getNegativeGiList(),
      config.getNegativeTaxIds(),
      config.getNegativeTaxIdList(),
      config.getSubject(),
      config.getSubjectLocation()
    );
  }

  static boolean forbidNegativeTaxIds(BlastnConfig config) {
    return config.isRemoteEnabled() || forbidList(
      config.getGiList(),
      config.getSequenceIdList(),
      config.getTaxIds(),
      config.getTaxIdList(),
      config.getNegativeSequenceIdList(),
      config.getNegativeGiList(),
      config.getNegativeTaxIdList(),
      config.getSubject(),
      config.getSubjectLocation()
    );
  }

  static boolean forbidTaxIdList(BlastnConfig config) {
    return config.isRemoteEnabled() || forbidList(
      config.getGiList(),
      config.getSequenceIdList(),
      config.getTaxIds(),
      config.getNegativeSequenceIdList(),
      config.getNegativeGiList(),
      config.getNegativeTaxIds(),
      config.getNegativeTaxIdList(),
      config.getSubject(),
      config.getSubjectLocation()
    );
  }

  static boolean forbidNegativeTaxIdList(BlastnConfig config) {
    return config.isRemoteEnabled() || forbidList(
      config.getGiList(),
      config.getSequenceIdList(),
      config.getTaxIds(),
      config.getTaxIdList(),
      config.getNegativeSequenceIdList(),
      config.getNegativeGiList(),
      config.getNegativeTaxIds(),
      config.getSubject(),
      config.getSubjectLocation()
    );
  }

  static boolean forbidNegSeqIdList(BlastnConfig config) {
    return config.isRemoteEnabled() || forbidList(
      config.getGiList(),
      config.getSequenceIdList(),
      config.getTaxIds(),
      config.getTaxIdList(),
      config.getNegativeGiList(),
      config.getNegativeTaxIds(),
      config.getNegativeTaxIdList(),
      config.getSubject(),
      config.getSubjectLocation()
    );
  }

  static boolean forbidSubject(BlastnConfig config) {
    // FIXME: DB is not actually used
    return config.getBlastDatabase() != null || forbidList(
      config.getGiList(),
      config.getSequenceIdList(),
      config.getNegativeGiList(),
      config.getNegativeSequenceIdList(),
      config.getTaxIds(),
      config.getTaxIdList(),
      config.getNegativeTaxIds(),
      config.getNegativeTaxIdList(),
      config.getDbSoftMask(),
      config.getDbHardMask()
    );
  }

  static boolean forbidList(Object... values) {
    for (var obj : values)
      if (obj != null)
        return true;

    return false;
  }

  static void validateWordSize(ErrorMap err, BlastnConfig conf) {
    if (conf.getWordSize() == null)
      return;

    if (conf.getWordSize() < 4)
      err.putError(ToolOption.WordSize, "must be greater than or equal to 4");
  }

}
