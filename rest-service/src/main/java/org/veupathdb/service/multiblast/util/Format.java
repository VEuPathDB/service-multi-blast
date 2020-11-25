package org.veupathdb.service.multiblast.util;

import java.text.DecimalFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Format
{
  public static final DecimalFormat Decimals = new DecimalFormat("0.##########");
  public static final ObjectMapper  Json     = new ObjectMapper();
}
