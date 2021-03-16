plugins {
  `java-platform`
}

group = "org.veupathdb.bom"
version = "1.0.0"

repositories {
  mavenLocal()
  mavenCentral()
}

val vCoreLib = "5.1.7"  // Container core lib version
val vJersey  = "2.33"   // Jersey/JaxRS version
val vJackson = "2.12.1" // FasterXML Jackson version
val junit    = "5.7.0"  // JUnit version
val vLog4j   = "2.14.0" // Log4J version
val vMetrics = "0.9.0"  // Prometheus lib version

dependencies {

  constraints {
    // Core Library
    api("org.veupathdb.lib:jaxrs-container-core:${vCoreLib}")

    // Jersey/Grizzly
    api("org.glassfish.jersey.containers:jersey-container-grizzly2-http:${vJersey}")
    api("org.glassfish.jersey.containers:jersey-container-grizzly2-servlet:${vJersey}")
    api("org.glassfish.jersey.media:jersey-media-json-jackson:${vJersey}")
    api("org.glassfish.jersey.media:jersey-media-multipart:${vJersey}")
    runtime("org.glassfish.jersey.inject:jersey-hk2:${vJersey}")

    // Jackson
    api("com.fasterxml.jackson.core:jackson-databind:${vJackson}")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:${vJackson}")

    runtime("com.fasterxml.jackson.module:jackson-module-parameter-names:${vJackson}")
    runtime("com.fasterxml.jackson.datatype:jackson-datatype-json-org:${vJackson}")
    runtime("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:${vJackson}")
    runtime("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${vJackson}")

    // Log4J
    api("org.apache.logging.log4j:log4j-api:${vLog4j}")
    api("org.apache.logging.log4j:log4j-core:${vLog4j}")
    api("org.apache.logging.log4j:log4j:${vLog4j}")

    // Metrics
    api("io.prometheus:simpleclient:${vMetrics}")
    api("io.prometheus:simpleclient_common:${vMetrics}")

    // Utils
    api("io.vulpine.lib:Jackfish:1.+")
    api("io.vulpine.lib:sql-import:0.2.1")
    api("io.vulpine.lib:lib-query-util:2.1.0")
    api("io.vulpine.lib:iffy:1.0.1")
    api("com.devskiller.friendly-id:friendly-id:1.+")

    runtime("org.json:json:20190722")
    runtime("org.apache.commons:commons-dbcp2:2.+")

    // CLI
    api("info.picocli:picocli:4.5.2")
  }
}