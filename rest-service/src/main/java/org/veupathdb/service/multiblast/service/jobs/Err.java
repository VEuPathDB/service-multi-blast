package org.veupathdb.service.multiblast.service.jobs;

import org.veupathdb.service.multiblast.model.io.JsonKeys;

class Err
{
  public static final String
    LessThanZero = "must be >= 0",
    LessThanOne  = "must be >= 1",

  // Base Blast
  RequireRemote = "this field must be 'true' when using the \""+ JsonKeys.EntrezQuery +"\" field",

  // Std Blast
  SubjectCompatibility = makeListError(
    "is incompatible with ",
    JsonKeys.DB,
    JsonKeys.GI_LIST,
    JsonKeys.SEQ_ID_LIST,
    JsonKeys.NEGATIVE_GI_LIST,
    JsonKeys.NEGATIVE_SEQ_ID_LIST,
    JsonKeys.TAX_IDS,
    JsonKeys.TAX_ID_LIST,
    JsonKeys.NEGATIVE_TAX_IDS,
    JsonKeys.NEGATIVE_TAX_ID_LIST,
    JsonKeys.DB_SOFT_MASK,
    JsonKeys.DB_HARD_MASK
  ),

  ForbidGiList = makeListError(
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

  ForbidSeqIdList = makeListError(
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

  ForbidNegativeGiList = makeListError(
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

  ForbidNegativeSeqIdList = makeListError(
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

  ForbidTaxIds = makeListError(
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

  ForbidNegativeTaxIds = makeListError(
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

  ForbidTaxIdList = makeListError(
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

  ForbidNegativeTaxIdList = makeListError(
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
