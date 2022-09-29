package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "tool"
)
@JsonSubTypes({
    @JsonSubTypes.Type(org.veupathdb.service.demo.generated.model.BlastPConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.demo.generated.model.PSIBlastConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.demo.generated.model.BlastNConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.demo.generated.model.TBlastNConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.demo.generated.model.RPSBlastConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.demo.generated.model.BlastXConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.demo.generated.model.RPSTBlastNConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.demo.generated.model.TBlastXConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.demo.generated.model.DeltaBlastConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.demo.generated.model.BlastQueryConfig.class)
})
@JsonDeserialize(
    as = BlastQueryConfigImpl.class
)
public interface BlastQueryConfig {
  BlastQueryTool _DISCRIMINATOR_TYPE_NAME = null;

  @JsonProperty("tool")
  BlastQueryTool getTool();

  @JsonProperty("queryLocation")
  BlastLocation getQueryLocation();

  @JsonProperty("queryLocation")
  void setQueryLocation(BlastLocation queryLocation);

  @JsonProperty(
      value = "eValue",
      defaultValue = "10"
  )
  String getEValue();

  @JsonProperty(
      value = "eValue",
      defaultValue = "10"
  )
  void setEValue(String eValue);

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  boolean getSoftMasking();

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  void setSoftMasking(boolean softMasking);

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  boolean getLowercaseMasking();

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  void setLowercaseMasking(boolean lowercaseMasking);

  @JsonProperty("queryCoverageHSPPercent")
  double getQueryCoverageHSPPercent();

  @JsonProperty("queryCoverageHSPPercent")
  void setQueryCoverageHSPPercent(double queryCoverageHSPPercent);

  @JsonProperty("maxHSPs")
  int getMaxHSPs();

  @JsonProperty("maxHSPs")
  void setMaxHSPs(int maxHSPs);

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  int getMaxTargetSequences();

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  void setMaxTargetSequences(int maxTargetSequences);

  @JsonProperty("dbSize")
  byte getDbSize();

  @JsonProperty("dbSize")
  void setDbSize(byte dbSize);

  @JsonProperty("searchSpace")
  byte getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace(byte searchSpace);

  @JsonProperty("xDropoffUngapped")
  double getXDropoffUngapped();

  @JsonProperty("xDropoffUngapped")
  void setXDropoffUngapped(double xDropoffUngapped);

  @JsonProperty("windowSize")
  int getWindowSize();

  @JsonProperty("windowSize")
  void setWindowSize(int windowSize);

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  boolean getParseDefLines();

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  void setParseDefLines(boolean parseDefLines);
}
