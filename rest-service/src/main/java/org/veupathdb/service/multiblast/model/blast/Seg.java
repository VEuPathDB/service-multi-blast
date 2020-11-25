package org.veupathdb.service.multiblast.model.blast;

import java.math.BigDecimal;

public interface Seg
{
  int getWindow();
  Seg setWindow(int window);

  double getLowCut();
  Seg setLowCut(double lowCut);
  Seg setLowCut(BigDecimal lowCut);

  double getHighCut();
  Seg setHighCut(double highCut);
  Seg setHighCut(BigDecimal highCut);
}
