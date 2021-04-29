package mb.lib.blast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.databind.JsonNode;
import org.veupathdb.lib.blast.BlastWithLists;
import org.veupathdb.lib.blast.consts.Flag;

public class XBlastWithLists
{
  private static final Map<String, BiConsumer<BlastWithLists, JsonNode>> map = new HashMap<>(){{
    put(Flag.GIList, XBlastWithLists::setGIList);
    put(Flag.SequenceIDList, XBlastWithLists::setSequenceIDList);
    put(Flag.NegativeGIList, XBlastWithLists::setNegativeGIList);
    put(Flag.NegativeSequenceIDList, XBlastWithLists::setNegativeSequenceIDList);
    put(Flag.TaxIDs, XBlastWithLists::setTaxIDs);
    put(Flag.NegativeTaxIDs, XBlastWithLists::setNegativeTaxIDs);
    put(Flag.TaxIDList, XBlastWithLists::setTaxIDList);
    put(Flag.NegativeTaxIDList, XBlastWithLists::setNegativeTaxIDList);
  }};

  static boolean fromLegacyJSON(BlastWithLists base, String key, JsonNode val) {
    var fn = map.get(key);

    if (fn != null) {
      fn.accept(base, val);
      return true;
    }

    return false;
  }

  public static void setGIList(BlastWithLists b, JsonNode giList) {
    b.setGIList(giList.textValue());
  }

  public static void setSequenceIDList(BlastWithLists b, JsonNode sequenceIDList) {
    b.setSequenceIDList(sequenceIDList.textValue());
  }

  public static void setNegativeGIList(BlastWithLists b, JsonNode negativeGIList) {
    b.setNegativeGIList(negativeGIList.textValue());
  }

  public static void setNegativeSequenceIDList(BlastWithLists b, JsonNode negativeSequenceIDList) {
    b.setNegativeSequenceIDList(negativeSequenceIDList.textValue());
  }

  public static void setTaxIDs(BlastWithLists b, JsonNode taxIDs) {
    b.setTaxIDs(Arrays.asList(taxIDs.textValue().split(" *, *")));
  }

  public static void setNegativeTaxIDs(BlastWithLists b, JsonNode negativeTaxIDs) {
    b.setNegativeTaxIDs(Arrays.asList(negativeTaxIDs.textValue().split(" *, *")));
  }

  public static void setTaxIDList(BlastWithLists b, JsonNode taxIDList) {
    b.setTaxIDList(taxIDList.textValue());
  }

  public static void setNegativeTaxIDList(BlastWithLists b, JsonNode negativeTaxIDList) {
    b.setNegativeTaxIDList(negativeTaxIDList.textValue());
  }

}
