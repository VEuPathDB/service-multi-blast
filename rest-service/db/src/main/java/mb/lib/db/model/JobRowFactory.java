package mb.lib.db.model;

import java.time.OffsetDateTime;
import java.util.Objects;

import mb.lib.db.model.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobRowFactory
{
  private static final Logger log = LogManager.getLogger(JobRowFactory.class);

  private static JobRowFactory instance;

  private ShortUserJobRowConstructor newShortUserJobRow = ShortUserJobRowImpl::new;
  private FullJobRowConstructor      newFullJobRow      = FullJobRowImpl::new;
  private FullUserJobRowConstructor  newFullUserJobRow  = FullUserJobRowImpl::new;

  public static FullJobRow newFullJobRow(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config
  ) {
    return getInstance().createNewFullJobRow(hash, queueID, createdOn, deleteOn, config);
  }

  public FullJobRow createNewFullJobRow(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config
  ) {
    log.trace("JobRowFactory#createNewFullJobRow(byte[], int, OffsetDateTime, OffsetDateTime, String)");
    return newFullJobRow.create(hash, queueID, createdOn, deleteOn, config);
  }

  public void setFullJobRowConstructor(FullJobRowConstructor con) {
    newFullJobRow = Objects.requireNonNull(con);
  }

  public static ShortUserJobRow newShortUserJobRow(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    long userId,
    String description,
    long maxDlSize
  ) {
    return getInstance().createNewShortUserJobRow(
      hash,
      queueID,
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
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    long userId,
    String description,
    long maxDlSize
  ) {
    log.trace(
      "JobRowFactory#createNewShortUserJobRow(byte[], int, OffsetDateTime, OffsetDateTime, long, String)");
    return newShortUserJobRow.create(
      hash,
      queueID,
      createdOn,
      deleteOn,
      userId,
      description,
      maxDlSize
    );
  }

  public void setShortUserJobRowConstructor(ShortUserJobRowConstructor con) {
    newShortUserJobRow = Objects.requireNonNull(con);
  }


  public static FullUserJobRow newFullUserJobRow(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config,
    long userId,
    String description,
    long maxDlSize
  ) {
    return getInstance().createNewFullUserJobRow(
      hash,
      queueID,
      createdOn,
      deleteOn,
      config,
      userId,
      description,
      maxDlSize
    );
  }

  public FullUserJobRow createNewFullUserJobRow(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config,
    long userId,
    String description,
    long maxDlSize
  ) {
    log.trace(
      "JobRowFactory#createNewFullUserJobRow(byte[], int, OffsetDateTime, OffsetDateTime, String, long, String)");
    return newFullUserJobRow.create(
      hash,
      queueID,
      createdOn,
      deleteOn,
      config,
      userId,
      description,
      maxDlSize
    );
  }

  public void setFullUserJobRowConstructor(FullUserJobRowConstructor con) {
    newFullUserJobRow = Objects.requireNonNull(con);
  }

  public static JobRowFactory getInstance() {
    if (instance == null)
      return instance = new JobRowFactory();

    return instance;
  }

  @FunctionalInterface
  public interface ShortUserJobRowConstructor
  {
    ShortUserJobRow create(
      byte[] hash,
      int queueID,
      OffsetDateTime createdOn,
      OffsetDateTime deleteOn,
      long userID,
      String description,
      long maxDlSize
    );
  }

  @FunctionalInterface
  public interface FullJobRowConstructor
  {
    FullJobRow create(
      byte[] hash,
      int queueID,
      OffsetDateTime createdOn,
      OffsetDateTime deleteOn,
      String config
    );
  }

  @FunctionalInterface
  public interface FullUserJobRowConstructor
  {
    FullUserJobRow create(
      byte[] hash,
      int queueID,
      OffsetDateTime createdOn,
      OffsetDateTime deleteOn,
      String config,
      long userId,
      String description,
      long maxDlSize
    );
  }
}
