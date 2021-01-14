package mb.lib.db.model;

import java.time.OffsetDateTime;
import java.util.Objects;

public class JobRowFactory
{
  private static JobRowFactory instance;

  private RowConstructor             newRow;
  private ShortJobRowConstructor     newShortJobRow;
  private ShortUserJobRowConstructor newShortUserJobRow;
  private FullJobRowConstructor      newFullJobRow;
  private FullUserJobRowConstructor  newFullUserJobRow;

  public static Row newRow(byte[] jobHash) {
    return getInstance().createNewRow(jobHash);
  }

  public Row createNewRow(byte[] jobHash) {
    return newRow.create(jobHash);
  }

  public void setRowConstructor(RowConstructor con) {
    newRow = Objects.requireNonNull(con);
  }

  public static ShortJobRow newShortJobRow(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn
  ) {
    return getInstance().createNewShortJobRow(hash, queueID, createdOn, deleteOn);
  }

  public ShortJobRow createNewShortJobRow(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn
  ) {
    return newShortJobRow.create(hash, queueID, createdOn, deleteOn);
  }

  public void setShortJobRowConstructor(ShortJobRowConstructor con) {
    newShortJobRow = Objects.requireNonNull(con);
  }

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
    String description
  ) {
    return getInstance().createNewShortUserJobRow(
      hash,
      queueID,
      createdOn,
      deleteOn,
      userId,
      description
    );
  }

  public ShortUserJobRow createNewShortUserJobRow(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    long userId,
    String description
  ) {
    return newShortUserJobRow.create(
      hash,
      queueID,
      createdOn,
      deleteOn,
      userId,
      description
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
    String description
  ) {
    return getInstance().createNewFullUserJobRow(
      hash,
      queueID,
      createdOn,
      deleteOn,
      config,
      userId,
      description
    );
  }

  public FullUserJobRow createNewFullUserJobRow(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config,
    long userId,
    String description
  ) {
    return newFullUserJobRow.create(
      hash,
      queueID,
      createdOn,
      deleteOn,
      config,
      userId,
      description
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
  public interface RowConstructor
  {
    Row create(byte[] jobHash);
  }

  @FunctionalInterface
  public interface ShortJobRowConstructor
  {
    ShortJobRow create(
      byte[] jobHash,
      int queueID,
      OffsetDateTime createdOn,
      OffsetDateTime deleteOn
    );
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
      String description
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
      String description
    );
  }
}
