package org.veupathdb.lib.mblast.sdk.query.http

import com.fasterxml.jackson.annotation.JsonProperty
import org.veupathdb.lib.hash_id.HashID

data class JobCreateResponse(@JsonProperty("queryJobID") val queryJobID: HashID)