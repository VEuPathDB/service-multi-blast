package org.veupathdb.service.multiblast.service.jobs;

import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.blast.BlastnConfig;
import org.veupathdb.service.multiblast.model.blast.OptionName;
import org.veupathdb.service.multiblast.model.blast.ReportFormatType;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

public class BlastnValidator extends BlastValidator
{
  private static final String
    ErrSubjectCompatibility    = "is incompatible with "
    + JsonKeys.DB + ", "
    + JsonKeys.GI_LIST + ", "
    + JsonKeys.SEQ_ID_LIST + ", "
    + JsonKeys.NEGATIVE_GI_LIST + ", "
    + JsonKeys.NEGATIVE_SEQ_ID_LIST + ", "
    + JsonKeys.TAX_IDS + ", "
    + JsonKeys.TAX_ID_LIST + ", "
    + JsonKeys.NEGATIVE_TAX_IDS + ", "
    + JsonKeys.NEGATIVE_TAX_ID_LIST + ", "
    + JsonKeys.DB_SOFT_MASK + ", and "
    + JsonKeys.DB_HARD_MASK,
    ErrLessThanZero            = "must be >= 0",
    ErrLessThan1               = "must be >= 1",
    ErrForbidWithMaxTargetSeqs = "is incompatible with "
      + JsonKeys.MAX_TARGET_SEQS,
    ErrOnlyForFmtLte4          = "is incompatible with output formats other than "
      + ReportFormatType.PAIRWISE.ioName() + ", "
      + ReportFormatType.QUERY_ANCHORED_WITH_IDENTITIES.ioName() + ", "
      + ReportFormatType.QUERY_ANCHORED_WITHOUT_IDENTITIES.ioName() + ", "
      + ReportFormatType.FLAT_QUERY_ANCHORED_WITH_IDENTITIES.ioName() + ", or "
      + ReportFormatType.FLAT_QUERY_ANCHORED_WITHOUT_IDENTITIES,
    ErrOnlyForPairwise         = "is incompatible with formats other than "
      + ReportFormatType.PAIRWISE.ioName(),
    ErrForbidGiList            = makeListError(
      "is incompatible with",
      JsonKeys.SEQ_ID_LIST,
      JsonKeys.TAX_IDS,
      JsonKeys.TAX_ID_LIST,
      JsonKeys.NEGATIVE_GI_LIST,
      JsonKeys.NEGATIVE_SEQ_ID_LIST,
      JsonKeys.NEGATIVE_TAX_IDS,
      JsonKeys.NEGATIVE_TAX_ID_LIST,
      JsonKeys.REMOTE,
      JsonKeys.SUBJECT,
      JsonKeys.SUBJECT_LOC
    ),
    ErrForbidSeqIdList         = makeListError(
      "is incompatible with",
      JsonKeys.GI_LIST,
      JsonKeys.TAX_IDS,
      JsonKeys.TAX_ID_LIST,
      JsonKeys.NEGATIVE_GI_LIST,
      JsonKeys.NEGATIVE_SEQ_ID_LIST,
      JsonKeys.NEGATIVE_TAX_IDS,
      JsonKeys.NEGATIVE_TAX_ID_LIST,
      JsonKeys.REMOTE,
      JsonKeys.SUBJECT,
      JsonKeys.SUBJECT_LOC
    ),
    ErrForbidNegativeGiList    = makeListError(
      "is incompatible with",
      JsonKeys.GI_LIST,
      JsonKeys.SEQ_ID_LIST,
      JsonKeys.TAX_IDS,
      JsonKeys.TAX_ID_LIST,
      JsonKeys.NEGATIVE_SEQ_ID_LIST,
      JsonKeys.NEGATIVE_TAX_IDS,
      JsonKeys.NEGATIVE_TAX_ID_LIST,
      JsonKeys.REMOTE,
      JsonKeys.SUBJECT,
      JsonKeys.SUBJECT_LOC
    ),
    ErrForbidNegativeSeqIdList = makeListError(
      "is incompatible with",
      JsonKeys.GI_LIST,
      JsonKeys.SEQ_ID_LIST,
      JsonKeys.TAX_IDS,
      JsonKeys.TAX_ID_LIST,
      JsonKeys.NEGATIVE_GI_LIST,
      JsonKeys.NEGATIVE_TAX_IDS,
      JsonKeys.NEGATIVE_TAX_ID_LIST,
      JsonKeys.REMOTE,
      JsonKeys.SUBJECT,
      JsonKeys.SUBJECT_LOC
    ),
    ErrForbidTaxIds            = makeListError(
      "is incompatible with",
      JsonKeys.GI_LIST,
      JsonKeys.SEQ_ID_LIST,
      JsonKeys.TAX_ID_LIST,
      JsonKeys.NEGATIVE_GI_LIST,
      JsonKeys.NEGATIVE_SEQ_ID_LIST,
      JsonKeys.NEGATIVE_TAX_IDS,
      JsonKeys.NEGATIVE_TAX_ID_LIST,
      JsonKeys.REMOTE,
      JsonKeys.SUBJECT,
      JsonKeys.SUBJECT_LOC
    ),
    ErrForbidNegativeTaxIds    = makeListError(
      "is incompatible with",
      JsonKeys.GI_LIST,
      JsonKeys.SEQ_ID_LIST,
      JsonKeys.TAX_IDS,
      JsonKeys.TAX_ID_LIST,
      JsonKeys.NEGATIVE_GI_LIST,
      JsonKeys.NEGATIVE_SEQ_ID_LIST,
      JsonKeys.NEGATIVE_TAX_ID_LIST,
      JsonKeys.REMOTE,
      JsonKeys.SUBJECT,
      JsonKeys.SUBJECT_LOC
    ),
    ErrForbidTaxIdList         = makeListError(
      "is incompatible with",
      JsonKeys.GI_LIST,
      JsonKeys.SEQ_ID_LIST,
      JsonKeys.TAX_IDS,
      JsonKeys.NEGATIVE_GI_LIST,
      JsonKeys.NEGATIVE_SEQ_ID_LIST,
      JsonKeys.NEGATIVE_TAX_IDS,
      JsonKeys.NEGATIVE_TAX_ID_LIST,
      JsonKeys.REMOTE,
      JsonKeys.SUBJECT,
      JsonKeys.SUBJECT_LOC
    ),
    ErrForbidNegativeTaxIdList = makeListError(
      "is incompatible with",
      JsonKeys.GI_LIST,
      JsonKeys.SEQ_ID_LIST,
      JsonKeys.TAX_IDS,
      JsonKeys.TAX_ID_LIST,
      JsonKeys.NEGATIVE_GI_LIST,
      JsonKeys.NEGATIVE_SEQ_ID_LIST,
      JsonKeys.NEGATIVE_TAX_IDS,
      JsonKeys.REMOTE,
      JsonKeys.SUBJECT,
      JsonKeys.SUBJECT_LOC
    );

  private static BlastnValidator instance;


  public ErrorMap validateConfig(BlastnConfig config) {
    var out = super.validateConfig(config);

    if (config.getWordSize() < 4)
      errors.putError(JsonKeys.WORD_SIZE, "word size must be 4 or greater");

    if (config.getPenalty() > 0)
      errors.putError(JsonKeys.PENALTY, "penalty cannot be grater than 0");

    if (config.getReward() < 0)
      errors.putError(JsonKeys.REWARD, "reward cannot be less than 0");

    if (config.getSubject() != null && forbidSubject())
      errors.putError(JsonKeys.SUBJECT, ErrSubjectCompatibility);

    if (config.getSubjectLoc() != null && forbidSubject())
      errors.putError(JsonKeys.SUBJECT, ErrSubjectCompatibility);

    if (config.getNumDescriptions() != null) {
      if (config.getNumDescriptions() < 0)
        errors.putError(JsonKeys.NUM_DESCRIPTIONS, ErrLessThanZero);

      if (config.getOutFmt().hasFormat() && outFmt.getFormat().getValue() > 4)
        errors.putError(JsonKeys.NUM_DESCRIPTIONS, ErrOnlyForFmtLte4);

      if (config.getMaxTargetSeqs() != null)
        errors.putError(JsonKeys.NUM_DESCRIPTIONS, ErrForbidWithMaxTargetSeqs);
    }

    if (config.getNumAlignments() != null) {
      if (config.getNumAlignments() < 0)
        errors.putError(JsonKeys.NUM_ALIGNMENTS, ErrLessThanZero);

      if (config.getMaxTargetSeqs() != null)
        errors.putError(JsonKeys.NUM_ALIGNMENTS, ErrForbidWithMaxTargetSeqs);
    }

    if (config.getSortHits() != null && outFmt.hasFormat() && outFmt.getFormat().getValue() > 4)
      errors.putError(JsonKeys.SORT_HITS, ErrOnlyForFmtLte4);

    if (config.getSortHsps() != null && outFmt.hasFormat() && outFmt.getFormat().getValue() > 1)
      errors.putError(JsonKeys.SORT_HSPS, ErrOnlyForPairwise);

    if (config.getGiList() != null && forbidGiList())
      errors.putError(JsonKeys.GI_LIST, ErrForbidGiList);

    if (config.getSeqIdList() != null && forbidSeqIdList())
      errors.putError(JsonKeys.SEQ_ID_LIST, ErrForbidSeqIdList);

    if (config.getNegativeGiList() != null && forbidNegGiList())
      errors.putError(JsonKeys.NEGATIVE_GI_LIST, ErrForbidNegativeGiList);

    if (config.getNegativeSeqIdList() != null && forbidNegSeqIdList())
      errors.putError(JsonKeys.NEGATIVE_SEQ_ID_LIST, ErrForbidNegativeSeqIdList);

    if (config.getTaxIds() != null && forbidTaxIds())
      errors.putError(JsonKeys.TAX_IDS, ErrForbidTaxIds);

    if (config.getNegativeTaxIds() != null && forbidNegativeTaxIds())
      errors.putError(JsonKeys.TAX_IDS, ErrForbidNegativeTaxIds);

    if (config.getTaxIdList() != null && forbidTaxIdList())
      errors.putError(JsonKeys.TAX_IDS, ErrForbidTaxIdList);

    if (config.getNegativeTaxIdList() != null && forbidNegativeTaxIdList())
      errors.putError(JsonKeys.TAX_IDS, ErrForbidNegativeTaxIdList);

    if (config.getEntrezQuery() != null && remote == null || !remote)
      errors.putError(OptionName.ENTREZ_QUERY, ErrRequireRemote);

    if (config.getDbSoftMask() != null && forbidDbSoftMask())
      errors.putError(OptionName.DB_SOFT_MASK, ErrForbidDbSoftMask);

    if (config.getDbHardMask() != null && forbidDbHardMask())
      errors.putError(OptionName.DB_HARD_MASK, ErrForbidDbHardMask);
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
    return (config.getRemote() != null && config.getRemote()) || forbidList(
      config.getSeqIdList(),
      taxIds,
      taxIdList,
      negativeGiList,
      negativeSeqIdList,
      negativeTaxIds,
      negativeTaxIdList,
      subject,
      subjectLoc
    );
  }

  static boolean forbidSeqIdList(BlastnConfig config) {
    return (config.getRemote() != null && config.getRemote()) || forbidList(
      giList,
      taxIds,
      taxIdList,
      negativeGiList,
      negativeSeqIdList,
      negativeTaxIds,
      negativeTaxIdList,
      subject,
      subjectLoc
    );
  }

  static boolean forbidNegGiList(BlastnConfig config) {
    return (config.getRemote() != null && config.getRemote()) || forbidList(
      giList,
      config.getSeqIdList(),
      taxIds,
      taxIdList,
      negativeSeqIdList,
      negativeTaxIds,
      negativeTaxIdList,
      subject,
      subjectLoc
    );
  }

  static boolean forbidTaxIds(BlastnConfig config) {
    return (config.getRemote() != null && config.getRemote()) || forbidList(
      giList,
      config.getSeqIdList(),
      taxIdList,
      negativeSeqIdList,
      negativeGiList,
      negativeTaxIds,
      negativeTaxIdList,
      subject,
      subjectLoc
    );
  }

  static boolean forbidNegativeTaxIds(BlastnConfig config) {
    return (config.getRemote() != null && config.getRemote()) || forbidList(
      giList,
      config.getSeqIdList(),
      taxIds,
      taxIdList,
      negativeSeqIdList,
      negativeGiList,
      negativeTaxIdList,
      subject,
      subjectLoc
    );
  }

  static boolean forbidTaxIdList(BlastnConfig config) {
    return (config.getRemote() != null && config.getRemote()) || forbidList(
      giList,
      config.getSeqIdList(),
      taxIds,
      negativeSeqIdList,
      negativeGiList,
      negativeTaxIds,
      negativeTaxIdList,
      subject,
      subjectLoc
    );
  }

  static boolean forbidNegativeTaxIdList(BlastnConfig config) {
    return (config.getRemote() != null && config.getRemote()) || forbidList(
      giList,
      config.getSeqIdList(),
      taxIds,
      taxIdList,
      negativeSeqIdList,
      negativeGiList,
      negativeTaxIds,
      subject,
      subjectLoc
    );
  }

  static boolean forbidNegSeqIdList(BlastnConfig config) {
    return (config.getRemote() != null && config.getRemote()) || forbidList(
      giList,
      config.getSeqIdList(),
      taxIds,
      taxIdList,
      negativeGiList,
      negativeTaxIds,
      negativeTaxIdList,
      subject,
      subjectLoc
    );
  }

  static boolean forbidSubject(BlastnConfig config) {
    // FIXME: DB is not actually used
    return db != null || forbidList(
      giList,
      config.getSeqIdList(),
      negativeGiList,
      negativeSeqIdList,
      taxIds,
      taxIdList,
      negativeTaxIds,
      negativeTaxIdList,
      dbSoftMask,
      dbHardMask
    );
  }

  static boolean forbidList(Object... vals) {
    for (var obj : vals)
      if (obj != null)
        return true;

    return false;
  }

  static String makeListError(String prefix, String... fields) {
    var out  = new StringBuilder(prefix).append(' ');
    var and  = fields.length - 2;
    var last = fields.length - 1;

    for (int i = 0; i < fields.length; i++) {
      out.append(fields[i]);

      if (i == and) {
        out.append(", and ");
      } else if (i < last) {
        out.append(", ");
      }
    }

    return out.toString();
  }
}
