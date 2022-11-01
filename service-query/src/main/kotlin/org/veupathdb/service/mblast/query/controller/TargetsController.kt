package org.veupathdb.service.mblast.query.controller

import org.veupathdb.service.mblast.query.generated.resources.Targets
import org.veupathdb.service.mblast.query.service.targets.GetTargets

class TargetsController : Targets {
  override fun getTargets(): Targets.GetTargetsResponse {
    return Targets.GetTargetsResponse.respond200WithApplicationJson(GetTargets())
  }
}