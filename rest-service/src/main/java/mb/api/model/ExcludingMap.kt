package mb.api.model

import java.util.regex.Pattern

class ExcludingMap : HashMap<String, Any?>() {
  var additionalProperties: MutableSet<Pattern> = HashSet()

  override fun put(key: String, value: Any?) = if (additionalProperties.size == 0) {
    super.put(key, value)
  } else {
    setProperty(key, value)
  }

  override fun putAll(from: Map<out String, *>) {
    if (additionalProperties.size == 0) {
      super.putAll(from)
    } else {
      for (key in from.keys) {
        setProperty(key, from[key])
      }
    }
  }

  fun addAcceptedPattern(pattern: Pattern) {
    additionalProperties.add(pattern)
  }

  private fun setProperty(key: String, value: Any?): Any? {
    if (additionalProperties.size == 0) {
      return super.put(key, value)
    } else {
      for (p in additionalProperties) {
        if (p.matcher(key).matches()) {
          return super.put(key, value)
        }
      }
      throw IllegalArgumentException("property $key is invalid according to RAML type")
    }
  }
}