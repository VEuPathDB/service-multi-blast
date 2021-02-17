package mb.lib.db.model;

import java.time.OffsetDateTime;

import mb.lib.db.model.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobRowFactory
{
  private static final Logger log = LogManager.getLogger(JobRowFactory.class);

  private static JobRowFactory instance;

  public static FullJobRow newFullJobRow(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config,
    String query
  ) {
    return getInstance().createNewFullJobRow(hash, queueID, createdOn, deleteOn, config, query);
  }

  public FullJobRow createNewFullJobRow(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config,
    String query
  ) {
    log.trace("#createNewFullJobRow(...)");
    return new FullJobRowImpl(hash, queueID, createdOn, deleteOn, config, query);
  }

  public static ShortUserJobRow newShortUserJobRow(
    byte[] hash,
    int queueID,
    byte[] parentJobID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    long userId,
    String description,
    long maxDlSize
  ) {
    return getInstance().createNewShortUserJobRow(
      hash,
      queueID,
      parentJobID,
      createdOn,
      deleteOn,
      userId,
      description,
      maxDlSize
    );
  }

  public ShortUserJobRow createNewShortUserJobRow(
    byte[] hash,
    int queueID,
    byte[] parentJobID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    long userId,
    String description,
    long maxDlSize
  ) {
    log.trace("#createNewShortUserJobRow(...)");
    return new ShortUserJobRowImpl(
      hash,
      queueID,
      parentJobID,
      createdOn,
      deleteOn,
      userId,
      description,
      maxDlSize
    );
  }

  public static FullUserJobRow newFullUserJobRow(
    byte[] hash,
    int queueID,
    byte[] parentJobID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config,
    String query,
    long userId,
    String description,
    long maxDlSize
  ) {
    return getInstance().createNewFullUserJobRow(
      hash,
      queueID,
      parentJobID,
      createdOn,
      deleteOn,
      config,
      query,
      userId,
      description,
      maxDlSize
    );
  }

  public FullUserJobRow createNewFullUserJobRow(
    byte[] hash,
    int queueID,
    byte[] parentJobID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config,
    String query,
    long userId,
    String description,
    long maxDlSize
  ) {
    log.trace("#createNewFullUserJobRow(...)");

    return new FullUserJobRowImpl(
      hash,
      queueID,
      parentJobID,
      createdOn,
      deleteOn,
      config,
      query,
      userId,
      description,
      maxDlSize
    );
  }

  public static JobRowFactory getInstance() {
    if (instance == null)
      return instance = new JobRowFactory();

    return instance;
  }
}
