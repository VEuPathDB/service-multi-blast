package mb.api.service.conv;

import java.math.BigDecimal;
import java.util.Arrays;

import mb.api.model.blast.*;
import mb.api.model.blast.impl.*;
import mb.lib.blast.model.*;
import mb.lib.blast.model.impl.ReportFormatImpl;
import mb.lib.blast.model.n.BlastnConfig;
import mb.lib.blast.model.p.BlastpConfig;
import mb.lib.blast.model.tn.TBlastnConfig;
import mb.lib.blast.model.tx.TBlastXConfig;
import mb.lib.blast.model.x.BlastxConfig;

public class BlastConverter
{
  private static BlastConverter instance;

  public static BlastConverter getInstance() {
    if (instance == null)
      return instance = new BlastConverter();

    return instance;
  }

  static BlastConfig<?> toInternal(IOBlastConfig conf) {
    return getInstance().externalToInternal(conf);
  }

  public BlastConfig<?> externalToInternal(IOBlastConfig conf) {
    if (conf == null)
      return null;

    BlastConfig<?> out = switch (conf.getTool()) {
      case BlastN -> BlastnConverter.toInternal((IOBlastnConfig) conf);
      case BlastP -> BlastpConverter.toInternal((IOBlastpConfig) conf);
      case BlastX -> BlastxConverter.toInternal((IOBlastxConfig) conf);
      case TBlastN -> TBlastnConverter.toInternal((IOTBlastnConfig) conf);
      case TBlastX -> TBlastxConverter.toInternal((IOTBlastxConfig) conf);
      case PSIBlast -> throw new RuntimeException("psiblast is currently disallowed");
      case RPSBlast -> throw new RuntimeException("rpsblast is currently disallowed");
      case RPSTBlastN -> throw new RuntimeException("rpstblastn is currently disallowed");
    };

    out.setQuery(conf.getQuery())
      .setQueryLocation(BCC.toInternal(conf.getQueryLoc()))
      .setExpectValue(new BigDecimal(conf.getEValue()))
      .setReportFormat(toInternal(conf.getOutFormat()))
      .setNumDescriptions(conf.getNumDescriptions())
      .setNumAlignments(conf.getNumAlignments())
      .setLineLength(conf.getLineLength())
      .setHitSorting(conf.getSortHits())
      .setHSPSorting(toInternal(conf.getSortHSPs()))
      .enableLowercaseMasking(BCC.nullToFalse(conf.getLcaseMasking()))
      .setQueryCoveragePercentHSP(conf.getQCovHSPPerc())
      .setMaxHSPs(conf.getMaxHSPs())
      .setMaxTargetSequences(conf.getMaxTargetSeqs())
      .setEffectiveDatabaseSize(conf.getDbSize())
      .setEffectiveSearchSpaceLength(conf.getSearchSpace())
      .setExtensionDropoffUngapped(conf.getXDropUngap())
      .enableDefLineParsing(BCC.nullToFalse(conf.getParseDefLines()));

    return out;
  }

  public static IOBlastConfig toExternal(BlastConfig<?> conf) {
    return getInstance().internalToExternal(conf);
  }

  public IOBlastConfig internalToExternal(BlastConfig<?> conf) {
    if (conf == null)
      return null;

    var out = newExternal(conf);

    out.setQueryLoc(BCC.toExternal(conf.getQueryLocation()));
    out.setEValue(conf.getExpectValue().toString());
    out.setOutFormat(toExternal(conf.getReportFormat()));
    out.setNumDescriptions(conf.getNumDescriptions());
    out.setNumAlignments(conf.getNumAlignments());
    out.setLineLength(conf.getLineLength());
    out.setSortHits(conf.getHitSorting());
    out.setSortHSPs(toExternal(conf.getHspSorting()));
    out.setLcaseMasking(conf.isLowercaseMaskingEnabled() ? true : null);
    out.setQCovHSPPerc(conf.getQueryCoverageHspPercent());
    out.setMaxHSPs(conf.getMaxHsps());
    out.setMaxTargetSeqs(conf.getMaxTargetSequences());
    out.setDbSize(conf.getEffectiveDatabaseSize());
    out.setSearchSpace(conf.getEffectiveSearchSpaceLength());
    out.setXDropUngap(conf.getUngappedExtensionDropoff());
    out.setParseDefLines(conf.isDefLineParsingEnabled() ? true : null);

    return switch (out.getTool()) {
      case BlastN -> BlastnConverter.toExternal((IOBlastnConfig) out, (BlastnConfig) conf);
      case BlastP -> BlastpConverter.toExternal((IOBlastpConfig) out, (BlastpConfig) conf);
      case BlastX -> BlastxConverter.toExternal((IOBlastxConfig) out, (BlastxConfig) conf);
      case TBlastN -> TBlastnConverter.toExternal((IOTBlastnConfig) out, (TBlastnConfig) conf);
      case TBlastX -> TBlastxConverter.toExternal((IOTBlastxConfig) out, (TBlastXConfig) conf);
      case PSIBlast -> throw new RuntimeException("psiblast is currently disallowed");
      case RPSBlast -> throw new RuntimeException("rpsblast is currently disallowed");
      case RPSTBlastN -> throw new RuntimeException("rpstblastn is currently disallowed");
    };
  }

  static IOBlastConfig newExternal(BlastConfig<?> conf) {
    if (conf instanceof BlastnConfig)
      return new IOBlastnConfigImpl();
    if (conf instanceof BlastpConfig)
      return new IOBlastpConfigImpl();
    if (conf instanceof BlastxConfig)
      return new IOBlastxConfigImpl();
    if (conf instanceof TBlastnConfig)
      return new IOTBlastnConfigImpl();
    if (conf instanceof TBlastXConfig)
      return new IOTBlastxConfigImpl();

    throw new IllegalArgumentException("unrecognized blast config type");
  }

  static IOBlastFormat toExternal(BlastReportType val) {
    if (val == null)
      return null;

    return switch (val) {
      case Pairwise -> IOBlastFormat.PAIRWISE;
      case QueryAnchoredWithIdentities -> IOBlastFormat.QUERYANCHOREDWITHIDENTITIES;
      case QueryAnchoredWithoutIdentities -> IOBlastFormat.QUERYANCHOREDWITHOUTIDENTITIES;
      case FlatQueryAnchoredWithIdentities -> IOBlastFormat.FLATQUERYANCHOREDWITHIDENTITIES;
      case FlatQueryAnchoredWithoutIdentities -> IOBlastFormat.FLATQUERYANCHOREDWITHOUTIDENTITIES;
      case XML -> IOBlastFormat.XML;
      case Tabular -> IOBlastFormat.TABULAR;
      case TabularWithComments -> IOBlastFormat.TABULARWITHCOMMENTS;
      case TextASN1 -> IOBlastFormat.TEXTASN_1;
      case BinaryASN1 -> IOBlastFormat.BINARYASN_1;
      case CSV -> IOBlastFormat.CSV;
      case ArchiveASN1 -> IOBlastFormat.ARCHIVEASN_1;
      case SeqAlignJSON -> IOBlastFormat.SEQALIGNJSON;
      case MultiFileJSON -> IOBlastFormat.MULTIFILEJSON;
      case MultiFileXML2 -> IOBlastFormat.MULTIFILEXML2;
      case SingleFileJSON -> IOBlastFormat.SINGLEFILEJSON;
      case SingleFileXML2 -> IOBlastFormat.SINGLEFILEXML2;
      case SAM -> IOBlastFormat.SAM;
      case OrganismReport -> IOBlastFormat.ORGANISMREPORT;
    };
  }

  static IOHSPSorting toExternal(HSPSorting val) {
    if (val == null)
      return null;

    return switch (val) {
      case ByExpectValue -> IOHSPSorting.BYHSPEVALUE;
      case ByScore -> IOHSPSorting.BYHSPSCORE;
      case ByQueryStart -> IOHSPSorting.BYHSPQUERYSTART;
      case ByPercentIdentity -> IOHSPSorting.BYHSPPERCENTIDENTITY;
      case BySubjectStart -> IOHSPSorting.BYHSPSUBJECTSTART;
    };
  }

  static IOBlastReportFormat toExternal(BlastReportFormat fmt) {
    if (fmt == null)
      return null;

    var out = new IOBlastReportFormatImpl();

    out.setDelim(fmt.getDelimiter() == null ? null : fmt.getDelimiter());
    out.setFields(
      fmt.getReportFields() == null
        || fmt.getReportFields().length == 1
        && fmt.getReportFields()[0] == BlastReportField.Standard
      ? null
      : Arrays.asList(fmt.getReportFields()));
    out.setFormat(toExternal(fmt.getType()));

    return out;
  }

  static BlastReportType toInternal(IOBlastFormat val) {
    if (val == null)
      return null;

    return switch (val) {
      case PAIRWISE -> BlastReportType.Pairwise;
      case QUERYANCHOREDWITHIDENTITIES -> BlastReportType.QueryAnchoredWithIdentities;
      case QUERYANCHOREDWITHOUTIDENTITIES -> BlastReportType.QueryAnchoredWithoutIdentities;
      case FLATQUERYANCHOREDWITHIDENTITIES -> BlastReportType.FlatQueryAnchoredWithIdentities;
      case FLATQUERYANCHOREDWITHOUTIDENTITIES -> BlastReportType.FlatQueryAnchoredWithoutIdentities;
      case XML -> BlastReportType.XML;
      case TABULAR -> BlastReportType.Tabular;
      case TABULARWITHCOMMENTS -> BlastReportType.TabularWithComments;
      case TEXTASN_1 -> BlastReportType.TextASN1;
      case BINARYASN_1 -> BlastReportType.BinaryASN1;
      case CSV -> BlastReportType.CSV;
      case ARCHIVEASN_1 -> BlastReportType.ArchiveASN1;
      case SEQALIGNJSON -> BlastReportType.SeqAlignJSON;
      case MULTIFILEJSON -> BlastReportType.MultiFileJSON;
      case MULTIFILEXML2 -> BlastReportType.MultiFileXML2;
      case SINGLEFILEJSON -> BlastReportType.SingleFileJSON;
      case SINGLEFILEXML2 -> BlastReportType.SingleFileXML2;
      case SAM -> BlastReportType.SAM;
      case ORGANISMREPORT -> BlastReportType.OrganismReport;
    };
  }

  static BlastReportFormat toInternal(IOBlastReportFormat fmt) {
    if (fmt == null)
      return null;

    return new ReportFormatImpl(
      toInternal(fmt.getFormat()),
      fmt.getDelim(),
      fmt.getFields().toArray(BlastReportField[]::new)
    );
  }

  static HSPSorting toInternal(IOHSPSorting val) {
    if (val == null)
      return null;

    return switch(val) {
      case BYHSPEVALUE -> HSPSorting.ByExpectValue;
      case BYHSPSCORE -> HSPSorting.ByScore;
      case BYHSPQUERYSTART -> HSPSorting.ByQueryStart;
      case BYHSPPERCENTIDENTITY -> HSPSorting.ByPercentIdentity;
      case BYHSPSUBJECTSTART -> HSPSorting.BySubjectStart;
    };
  }
}
