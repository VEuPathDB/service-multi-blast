package org.veupathdb.lib.mblast.sdk.report.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.NonStringEnumException
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException

enum class BlastOutFormat {

  Pairwise,
  QueryAnchoredWithIdentities,
  QueryAnchoredNoIdentities,
  FlatQueryAnchoredWithIdentities,
  FlatQueryAnchoredNoIdentities,
  XML,
  Tabular,
  TabularWithComments,
  SeqAlignText,
  SeqAlignBinary,
  CSV,
  ASN1,
  SeqAlignJSON,
  MultiFileBlastJSON,
  MultiFileBlastXML2,
  SingleFileBlastJSON,
  SingleFileBlastXML2,
  SAM,
  OrganismReport,
  ;

  @JsonValue
  fun toJson(): JsonNode = TextNode(
    when (this) {
      Pairwise                        -> "pairwise"
      QueryAnchoredWithIdentities     -> "query-anchored-with-identities"
      QueryAnchoredNoIdentities       -> "query-anchored-no-identities"
      FlatQueryAnchoredWithIdentities -> "flat-query-anchored-with-identities"
      FlatQueryAnchoredNoIdentities   -> "flat-query-anchored-no-identities"
      XML                             -> "xml"
      Tabular                         -> "tabular"
      TabularWithComments             -> "tabular-with-comments"
      SeqAlignText                    -> "seqalign-text"
      SeqAlignBinary                  -> "seqalign-binary"
      CSV                             -> "csv"
      ASN1                            -> "asn1"
      SeqAlignJSON                    -> "seqalign-json"
      MultiFileBlastJSON              -> "multi-file-blast-json"
      MultiFileBlastXML2              -> "multi-file-blast-xml2"
      SingleFileBlastJSON             -> "single-file-blast-json"
      SingleFileBlastXML2             -> "single-file-blast-xml2"
      SAM                             -> "sam"
      OrganismReport                  -> "organism-report"
    }
  )

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): BlastOutFormat {
      if (!js.isTextual)
        throw NonStringEnumException(BlastOutFormat::class)

      return when (val v = js.textValue()) {
        "pairwise"                            -> Pairwise
        "query-anchored-with-identities"      -> QueryAnchoredWithIdentities
        "query-anchored-no-identities"        -> QueryAnchoredNoIdentities
        "flat-query-anchored-with-identities" -> FlatQueryAnchoredWithIdentities
        "flat-query-anchored-no-identities"   -> FlatQueryAnchoredNoIdentities
        "xml"                                 -> XML
        "tabular"                             -> Tabular
        "tabular-with-comments"               -> TabularWithComments
        "seqalign-text"                       -> SeqAlignText
        "seqalign-binary"                     -> SeqAlignBinary
        "csv"                                 -> CSV
        "asn1"                                -> ASN1
        "seqalign-json"                       -> SeqAlignJSON
        "multi-file-blast-json"               -> MultiFileBlastJSON
        "multi-file-blast-xml2"               -> MultiFileBlastXML2
        "single-file-blast-json"              -> SingleFileBlastJSON
        "single-file-blast-xml2"              -> SingleFileBlastXML2
        "sam"                                 -> SAM
        "organism-report"                     -> OrganismReport
        else                                  -> throw UnrecognizedEnumException(BlastOutFormat::class, v)
      }
    }
  }
}