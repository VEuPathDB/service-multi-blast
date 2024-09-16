package mb.api.model

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.contains
import jakarta.ws.rs.BadRequestException
import mb.api.model.blast.IOBlastConfig
import mb.api.model.dmnd.IODiamondConfig
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.jackson.Json

