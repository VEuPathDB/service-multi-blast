package mb.lib.format;

public enum FormatType
{
  Pairwise(0, false, false),
  QueryAnchoredWithIdentities(1, false, false),
  QueryAnchoredWithoutIdentities(2, false, false),
  FlatQueryAnchoredWithIdentities(3, false, false),
  FlatQueryAnchoredWithoutIdentities(4, false, false),
  BlastXML(5, false, false),
  Tabular(6, true, true),
  TabularWithCommentLines(7, true, true),
  SeqAlignTextASN1(8, false, false),
  SeqAlignBinaryASN1(9, false, false),
  CommaSeparatedValues(10, true, true),
  BlastArchiveASN1(11, false, false),
  SeqAlignJSON(12, false, false),
  MultipleFileBlastJSON(13, false, false),
  MultipleFileBlastXML2(14, false, false),
  SingleFileBlastJSON(15, false, false),
  SingleFileBlastXML2(16, false, false),
  SequenceAlignmentMap(17, true, false),
  OrganismReport(18, false, false),
  ;

  private final int id;
  private final boolean parameterized;
  private final boolean delimiterAllowed;


  FormatType(int id, boolean parameterized, boolean delimiterAllowed) {
    this.id            = id;
    this.parameterized = parameterized;
    this.delimiterAllowed = delimiterAllowed;
  }

  public int id() {
    return id;
  }

  public boolean isParameterized() {
    return parameterized;
  }

  public boolean isDelimiterAllowed() {
    return delimiterAllowed;
  }
}
