package mb.api.model.blast

import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.model.io.JsonKeys

interface IOBlastWithLists : IOBlastConfig {
  @get:JsonProperty(JsonKeys.TaxIDs)
  @set:JsonProperty(JsonKeys.TaxIDs)
  var taxIds: List<Int>?

  @get:JsonProperty(JsonKeys.NegativeTaxIDs)
  @set:JsonProperty(JsonKeys.NegativeTaxIDs)
  var negativeTaxIds: List<Int>?
}