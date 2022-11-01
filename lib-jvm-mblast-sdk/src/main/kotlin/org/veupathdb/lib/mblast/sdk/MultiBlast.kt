package org.veupathdb.lib.mblast.sdk

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.query.blast.BlastQueryConfig
import org.veupathdb.lib.mblast.sdk.query.http.TargetSite
import org.veupathdb.lib.mblast.sdk.util.BlastQueryConfigDeserializer
import org.veupathdb.lib.mblast.sdk.util.HashIDDeserializer
import org.veupathdb.lib.mblast.sdk.util.HashIDSerializer
import org.veupathdb.lib.mblast.sdk.util.TargetSiteDeserializer

object MultiBlast {
  @JvmStatic
  internal val JSON = ObjectMapper()
    .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
    .registerModule(JsonOrgModule())
    .registerModule(JavaTimeModule())
    .registerModule(Jdk8Module())
    .registerModule(
      KotlinModule.Builder()
      .enable(KotlinFeature.NullToEmptyMap)
      .enable(KotlinFeature.NullToEmptyCollection)
      .enable(KotlinFeature.SingletonSupport)
      .build())
    .registerModule(ParameterNamesModule())
    .registerModule(SimpleModule().also {
      it.addSerializer(HashID::class.java, HashIDSerializer())
      it.addDeserializer(HashID::class.java, HashIDDeserializer())
      it.addDeserializer(BlastQueryConfig::class.java, BlastQueryConfigDeserializer())
      it.addDeserializer(TargetSite::class.java, TargetSiteDeserializer())
    })

  fun newClient(fn: MultiBlastClientConfigBuilder.() -> Unit): MultiBlastClient =
    MultiBlastClientImpl(MultiBlastClientConfigBuilderImpl().also(fn).build())
}