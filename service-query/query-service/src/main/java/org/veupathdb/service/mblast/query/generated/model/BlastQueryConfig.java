package org.veupathdb.service.mblast.query.generated.model;

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
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.BlastPConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.PSIBlastConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.BlastNConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.TBlastNConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.RPSBlastConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.BlastXConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.RPSTBlastNConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.TBlastXConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.DeltaBlastConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.mblast.query.generated.model.BlastQueryConfig.class)
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
  Boolean getSoftMasking();

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  void setSoftMasking(Boolean softMasking);

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  Boolean getLowercaseMasking();

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  void setLowercaseMasking(Boolean lowercaseMasking);

  @JsonProperty("queryCoverageHSPPercent")
  Double getQueryCoverageHSPPercent();

  @JsonProperty("queryCoverageHSPPercent")
  void setQueryCoverageHSPPercent(Double queryCoverageHSPPercent);

  @JsonProperty("maxHSPs")
  Integer getMaxHSPs();

  @JsonProperty("maxHSPs")
  void setMaxHSPs(Integer maxHSPs);

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  Integer getMaxTargetSequences();

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  void setMaxTargetSequences(Integer maxTargetSequences);

  @JsonProperty("dbSize")
  Byte getDbSize();

  @JsonProperty("dbSize")
  void setDbSize(Byte dbSize);

  @JsonProperty("searchSpace")
  Byte getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace(Byte searchSpace);

  @JsonProperty("xDropoffUngapped")
  Double getXDropoffUngapped();

  @JsonProperty("xDropoffUngapped")
  void setXDropoffUngapped(Double xDropoffUngapped);

  @JsonProperty("windowSize")
  Integer getWindowSize();

  @JsonProperty("windowSize")
  void setWindowSize(Integer windowSize);

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  Boolean getParseDefLines();

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  void setParseDefLines(Boolean parseDefLines);
}
