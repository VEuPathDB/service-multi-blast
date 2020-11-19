package org.veupathdb.service.multiblast.service.valid;

import org.veupathdb.service.multiblast.model.blast.ReportFormatType;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

class Err
{
  public static final String
    LessThanZero = "must be >= 0",
    LessThanOne  = "must be >= 1",

  // Base Blast
  RequireRemote = "this field must be 'true' when using the \"" + JsonKeys.EntrezQuery + "\" field",

  // Std Blast
  SubjectCompatibility = makeListError(
    "is incompatible with ",
    JsonKeys.BlastDatabase,
    JsonKeys.GI_LIST,
    JsonKeys.SEQ_ID_LIST,
    JsonKeys.NEGATIVE_GI_LIST,
    JsonKeys.NEGATIVE_SEQ_ID_LIST,
    JsonKeys.TaxIDs,
    JsonKeys.TAX_ID_LIST,
    JsonKeys.NegativeTaxIDs,
    JsonKeys.NEGATIVE_TAX_ID_LIST,
    JsonKeys.DBSoftMask,
    JsonKeys.DBHardMask
  ),

  ForbidGiList = makeListError(
    "is incompatible with",
    JsonKeys.SEQ_ID_LIST,
    JsonKeys.TaxIDs,
    JsonKeys.TAX_ID_LIST,
    JsonKeys.NEGATIVE_GI_LIST,
    JsonKeys.NEGATIVE_SEQ_ID_LIST,
    JsonKeys.NegativeTaxIDs,
    JsonKeys.NEGATIVE_TAX_ID_LIST,
    JsonKeys.REMOTE,
    JsonKeys.SUBJECT,
    JsonKeys.SubjectLocation
  ),

  ForbidSeqIdList = makeListError(
    "is incompatible with",
    JsonKeys.GI_LIST,
    JsonKeys.TaxIDs,
    JsonKeys.TAX_ID_LIST,
    JsonKeys.NEGATIVE_GI_LIST,
    JsonKeys.NEGATIVE_SEQ_ID_LIST,
    JsonKeys.NegativeTaxIDs,
    JsonKeys.NEGATIVE_TAX_ID_LIST,
    JsonKeys.REMOTE,
    JsonKeys.SUBJECT,
    JsonKeys.SubjectLocation
  ),

  ForbidNegativeGiList = makeListError(
    "is incompatible with",
    JsonKeys.GI_LIST,
    JsonKeys.SEQ_ID_LIST,
    JsonKeys.TaxIDs,
    JsonKeys.TAX_ID_LIST,
    JsonKeys.NEGATIVE_SEQ_ID_LIST,
    JsonKeys.NegativeTaxIDs,
    JsonKeys.NEGATIVE_TAX_ID_LIST,
    JsonKeys.REMOTE,
    JsonKeys.SUBJECT,
    JsonKeys.SubjectLocation
  ),

  ForbidNegativeSeqIdList = makeListError(
    "is incompatible with",
    JsonKeys.GI_LIST,
    JsonKeys.SEQ_ID_LIST,
    JsonKeys.TaxIDs,
    JsonKeys.TAX_ID_LIST,
    JsonKeys.NEGATIVE_GI_LIST,
    JsonKeys.NegativeTaxIDs,
    JsonKeys.NEGATIVE_TAX_ID_LIST,
    JsonKeys.REMOTE,
    JsonKeys.SUBJECT,
    JsonKeys.SubjectLocation
  ),

  ForbidTaxIds = makeListError(
    "is incompatible with",
    JsonKeys.GI_LIST,
    JsonKeys.SEQ_ID_LIST,
    JsonKeys.TAX_ID_LIST,
    JsonKeys.NEGATIVE_GI_LIST,
    JsonKeys.NEGATIVE_SEQ_ID_LIST,
    JsonKeys.NegativeTaxIDs,
    JsonKeys.NEGATIVE_TAX_ID_LIST,
    JsonKeys.REMOTE,
    JsonKeys.SUBJECT,
    JsonKeys.SubjectLocation
  ),

  ForbidNegativeTaxIds = makeListError(
    "is incompatible with",
    JsonKeys.GI_LIST,
    JsonKeys.SEQ_ID_LIST,
    JsonKeys.TaxIDs,
    JsonKeys.TAX_ID_LIST,
    JsonKeys.NEGATIVE_GI_LIST,
    JsonKeys.NEGATIVE_SEQ_ID_LIST,
    JsonKeys.NEGATIVE_TAX_ID_LIST,
    JsonKeys.REMOTE,
    JsonKeys.SUBJECT,
    JsonKeys.SubjectLocation
  ),

  ForbidTaxIdList = makeListError(
    "is incompatible with",
    JsonKeys.GI_LIST,
    JsonKeys.SEQ_ID_LIST,
    JsonKeys.TaxIDs,
    JsonKeys.NEGATIVE_GI_LIST,
    JsonKeys.NEGATIVE_SEQ_ID_LIST,
    JsonKeys.NegativeTaxIDs,
    JsonKeys.NEGATIVE_TAX_ID_LIST,
    JsonKeys.REMOTE,
    JsonKeys.SUBJECT,
    JsonKeys.SubjectLocation
  ),

  ForbidNegativeTaxIdList = makeListError(
    "is incompatible with",
    JsonKeys.GI_LIST,
    JsonKeys.SEQ_ID_LIST,
    JsonKeys.TaxIDs,
    JsonKeys.TAX_ID_LIST,
    JsonKeys.NEGATIVE_GI_LIST,
    JsonKeys.NEGATIVE_SEQ_ID_LIST,
    JsonKeys.NegativeTaxIDs,
    JsonKeys.REMOTE,
    JsonKeys.SUBJECT,
    JsonKeys.SubjectLocation
  ),

  OnlyForFmtLte4 = "is incompatible with output formats other than "
    + ReportFormatType.Pairwise.getIoName() + ", "
    + ReportFormatType.QueryAnchoredWithIdentities.getIoName() + ", "
    + ReportFormatType.QueryAnchoredWithoutIdentities.getIoName() + ", "
    + ReportFormatType.FlatQueryAnchoredWithIdentities.getIoName() + ", or "
    + ReportFormatType.FlatQueryAnchoredWithoutIdentities.getIoName(),

  ForbidWithMaxTargetSeqs = "is incompatible with " + JsonKeys.MaxTargetSequences,

  OnlyForPairwise = "is incompatible with formats other than "
    + ReportFormatType.Pairwise.getIoName();


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
