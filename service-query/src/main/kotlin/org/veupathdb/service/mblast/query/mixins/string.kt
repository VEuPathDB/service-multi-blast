package org.veupathdb.service.mblast.query.mixins

import org.veupathdb.service.mblast.query.generated.model.TargetSite

/**
 * Translates the target string into a [TargetSite] instance.
 *
 * If the target string does not contain a valid `TargetSite` value, this method
 * throws an exception.
 *
 * @receiver String containing the `TargetSite` value to translate.
 *
 * @return The translated `TargetSite` value.
 *
 * @throws IllegalArgumentException If the receiver string does not contain a
 * valid `TargetSite` value.
 */
fun String.toTargetSite() =
  TargetSite.values().let {
    it.forEach {
      if (it.value == this)
        return@let it
    }

    throw IllegalArgumentException("Unrecognized target site: $this")
  }
