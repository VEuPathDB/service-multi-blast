package mb.lib.blast;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import org.veupathdb.lib.blast.BlastWithIPGList;
import org.veupathdb.lib.blast.consts.Flag;

public class XBlastWithIPGList
{
  private static final Map<String, BiConsumer<BlastWithIPGList, JsonNode>> map = new HashMap<>(){{
    put(Flag.IPGList, XBlastWithIPGList::setIPGList);
    put(Flag.NegativeIPGList, XBlastWithIPGList::setNegativeIPGList);
  }};

  static boolean fromLegacyJSON(BlastWithIPGList base, String key, JsonNode val) {
    var fn = map.get(key);

    if (fn != null) {
      fn.accept(base, val);
      return true;
    }

    return false;
  }

  public static void setIPGList(BlastWithIPGList b, JsonNode giList) {
    b.setIPGList(giList.asText());
  }


  public static void setNegativeIPGList(BlastWithIPGList b, JsonNode negativeIPGList) {
    b.setNegativeIPGList(negativeIPGList.asText());
  }
}
