package mb.api.model.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.blast.field.FormatType

enum class IOBlastFormat(val value: String) {
  Pairwise                           ("pairwise"),
  QueryAnchoredWithIdentities        ("query-anchored-with-identities"),
  QueryAnchoredWithoutIdentities     ("query-anchored-without-identities"),
  FlagQueryAnchoredWithIdentities    ("flat-query-anchored-with-identities"),
  FlatQueryAnchoredWithoutIdentities ("flat-query-anchored-without-identities"),
  XML                                ("xml"),
  Tabular                            ("tabular"),
  TabularWithComments                ("tabular-with-comments"),
  TextASN1                           ("text-asn-1"),
  BinaryASN1                         ("binary-asn-1"),
  CSV                                ("csv"),
  ArchiveASN1                        ("archive-asn-1"),
  SeqAlignJSON                       ("seqalign-json"),
  MultifileJSON                      ("multi-file-json"),
  MultifileXML2                      ("multi-file-xml2"),
  SingleFileJSON                     ("single-file-json"),
  SingleFileXML2                     ("single-file-xml2"),
  SAM                                ("sam"),
  OrganismReport                     ("organism-report");

  val internalValue get() = FormatType.values()[ordinal]

  @JsonValue
  fun toJSON(): JsonNode = TextNode(value)

  companion object {
    @JvmStatic
    @JsonCreator
    fun fromJSON(value: JsonNode) = with(value.textValue()) {
      values().forEach {
        if (it.value == this)
          return@with it
      }

      throw IllegalArgumentException("Unrecognized IOBlastFormat value \"$this\".")
    }

    fun fromInternalValue(value: FormatType?) = if (value == null) null else values()[value.ordinal]
  }
}
