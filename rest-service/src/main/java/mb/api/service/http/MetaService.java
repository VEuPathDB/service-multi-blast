package mb.api.service.http;

import java.io.File;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import mb.api.model.*;
import mb.lib.config.Config;


public class MetaService
{
  private static final TemporalAmount syncRate = Duration.ofMinutes(10);
  private static final String         blastDir = "blast";

  private static MetaService instance;

  private final MetaResponse  dbs;
  private       LocalDateTime lastSync;

  private MetaService() {
    dbs      = new MetaResponseImpl();
    lastSync = LocalDateTime.ofInstant(Instant.ofEpochSecond(0), ZoneId.systemDefault());
  }

  static MetaService getInstance() {
    if (instance == null)
      return instance = new MetaService();
    return instance;
  }

  OutputListOrganism fileToListOrg(File orgDir) {
    var out = new OutputListOrganismImpl();
    out.setName(orgDir.toPath().getParent().toFile().getName());

    var databases = Arrays.stream(Objects.requireNonNull(orgDir.listFiles(File::isFile)))
      .map(File::getName)
      .map(MetaService::removeSuffix)
      .distinct()
      .toArray(String[]::new);

    out.setBlast(Arrays.asList(databases));

    return out;
  }

  void resyncFileTree() {
    synchronized (dbs) {
      // Only resynchronize once every `syncRate` time window.
      if (LocalDateTime.now().isBefore(lastSync.plus(syncRate))) {
        return;
      }

      var root = new File(Config.getInstance().getDbMountPath());
      if (!root.exists())
        throw new IllegalStateException("Blast mount path is invalid or missing.");
      if (!root.isDirectory())
        throw new IllegalStateException("Blast mount path points to a file, not a directory.");

      var buildDir = "build-" + Config.getInstance().getBuildNum();

      var sites = Arrays.stream(Objects.requireNonNull(root.listFiles(File::isDirectory)))
        .filter(f -> f.toPath().resolve(buildDir).toFile().exists())
        .toArray(File[]::new);

      var tgts = new OutputSiteListingImpl();

      for (var site : sites) {
        var siteDir = site.toPath().resolve(buildDir).toFile();
        var orgs = Arrays.stream(Objects.requireNonNull(siteDir.listFiles(File::isDirectory)))
          .map(File::toPath)
          .map(MetaService::resolveBlastDir)
          .map(Path::toFile)
          .filter(File::exists)
          .map(this::fileToListOrg)
          .collect(Collectors.toList());
        tgts.setAdditionalProperties(site.getName(), orgs);
      }

      dbs.setTargets(tgts);
      lastSync = LocalDateTime.now();
    }
  }

  public static MetaResponse fileTree() {
    return getInstance().getFileTree();
  }

  public MetaResponse getFileTree() {
    resyncFileTree();
    return dbs;
  }

  static String removeSuffix(String name) {
    return name.substring(0, name.lastIndexOf('.'));
  }

  static Path resolveBlastDir(Path parent) {
    return parent.resolve(blastDir);
  }
}
