package mb.api.service.http.job;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import mb.api.model.*;
import mb.api.service.util.Format;
import mb.lib.query.model.BlastJobLink;
import mb.lib.query.model.BlastTargetLink;
import mb.lib.query.model.FullUserBlastRow;
import mb.lib.util.BlastConv;

class Util
{
  static IOLongJobResponse translateLongResponse(FullUserBlastRow row) {
    return (IOLongJobResponse) new IOLongJobResponseImpl()
      .setConfig(BlastConv.convert(row.getConfig()))
      .setId(row.getJobID().string())
      .setDescription(row.getDescription())
      .setStatus(row.getStatus())
      .setCreated(row.getCreatedOn().format(Format.DateFormat))
//        .setExpires(row.getDeleteOn().format(Format.DateFormat))
      .setMaxResultSize(row.getMaxDownloadSize())
      .setParentJobs(Util.translateParentLinks(row.getParentJobs()))
      .setChildJobs(Util.translateChildLinks(row.getChildJobs()))
      .setIsPrimary(row.isRunDirectly())
      .setSite(row.getProjectID())
      .setTargets(Util.translateTargets(row.getTargetDBs()));
  }

  static List<IOShortJobResponse> translateShortResponses(Collection<FullUserBlastRow> rows) {
    return rows.stream()
      .map(Util::translateShortResponse)
      .collect(Collectors.toList());
  }

  static IOShortJobResponse translateShortResponse(FullUserBlastRow row) {
    return new IOShortJobResponseImpl()
      .setId(row.getJobID().string())
      .setDescription(row.getDescription())
      .setStatus(row.getStatus())
      .setCreated(row.getCreatedOn().format(Format.DateFormat))
//        .setExpires(row.getDeleteOn().format(Format.DateFormat))
      .setMaxResultSize(row.getMaxDownloadSize())
      .setParentJobs(Util.translateParentLinks(row.getParentJobs()))
      .setChildJobs(Util.translateChildLinks(row.getChildJobs()))
      .setIsPrimary(row.isRunDirectly())
      .setSite(row.getProjectID())
      .setTargets(Util.translateTargets(row.getTargetDBs()));
  }

  static IOJobTarget[] translateTargets(List<BlastTargetLink> links) {
    return links.stream()
      .map(Util::translateTarget)
      .toArray(IOJobTarget[]::new);
  }

  static IOJobTarget translateTarget(BlastTargetLink link) {
    return new IOJobTargetImpl()
      .target(link.getTargetFile())
      .organism(link.getOrganism());
  }

  static IOJobLink[] translateParentLinks(List<BlastJobLink> links) {
    return links.stream()
      .map(Util::translateParentLink)
      .toArray(IOJobLink[]::new);
  }

  static IOJobLink translateParentLink(BlastJobLink link) {
    return new IOJobLinkImpl()
      .setId(link.getParentJobID().string())
      .setIndex(link.getPosition());
  }

  static IOJobLink[] translateChildLinks(List<BlastJobLink> links) {
    return links.stream()
      .map(Util::translateChildLink)
      .toArray(IOJobLink[]::new);
  }

  static IOJobLink translateChildLink(BlastJobLink link) {
    return new IOJobLinkImpl()
      .setId(link.getChildJobID().string())
      .setIndex(link.getPosition());
  }
}
