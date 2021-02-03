plugins {
  java
}

java {
  targetCompatibility = JavaVersion.VERSION_15
  sourceCompatibility = JavaVersion.VERSION_15
}

val junit = "5.7.0"
dependencies {
  implementation(platform(project(":bom")))

  implementation(project(":db"))
  implementation(project(":queue"))
  implementation(project(":config"))
  implementation(project(":job-data"))
  implementation(project(":formatter"))

  //
  // FgpUtil & Compatibility Dependencies
  //

  // FgpUtil jars
  implementation(files(
    "${rootProject.projectDir}/vendor/fgputil-accountdb-1.0.0.jar",
    "${rootProject.projectDir}/vendor/fgputil-core-1.0.0.jar",
    "${rootProject.projectDir}/vendor/fgputil-db-1.0.0.jar",
    "${rootProject.projectDir}/vendor/fgputil-web-1.0.0.jar"
  ))

  // Compatibility bridge to support the long dead log4j-1.X
  runtimeOnly("org.apache.logging.log4j:log4j-1.2-api")

  // Extra FgpUtil dependencies
  runtimeOnly("org.apache.commons:commons-dbcp2")
  runtimeOnly("org.json:json")
  runtimeOnly("com.fasterxml.jackson.datatype:jackson-datatype-json-org")
  runtimeOnly("com.fasterxml.jackson.module:jackson-module-parameter-names")
  runtimeOnly("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
  runtimeOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

  //
  // Project Dependencies
  //

  // Oracle
  runtimeOnly(files(
    "${rootProject.projectDir}/vendor/ojdbc8.jar",
    "${rootProject.projectDir}/vendor/ucp.jar",
    "${rootProject.projectDir}/vendor/xstreams.jar"
  ))


  // Core lib, prefers local checkout if available
  implementation("org.veupathdb.lib:jaxrs-container-core")


  // Jersey
  implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http")
  implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-servlet")
  implementation("org.glassfish.jersey.media:jersey-media-json-jackson")
  implementation("jakarta.ws.rs:jakarta.ws.rs-api")
  runtimeOnly("org.glassfish.jersey.inject:jersey-hk2")

  // Jackson
  implementation("com.fasterxml.jackson.core:jackson-databind")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")

  // Log4J
  implementation("org.apache.logging.log4j:log4j-api")
  implementation("org.apache.logging.log4j:log4j-core")
  implementation("org.apache.logging.log4j:log4j")

  // Metrics
  implementation("io.prometheus:simpleclient")
  implementation("io.prometheus:simpleclient_common")

  // Utils
  implementation("io.vulpine.lib:Jackfish")
  implementation("io.vulpine.lib:iffy")
  implementation("com.devskiller.friendly-id:friendly-id")

  // Unit Testing
  testImplementation("org.junit.jupiter:junit-jupiter-api:${junit}")
  testImplementation("org.junit.jupiter:junit-jupiter-params:${junit}")
  testImplementation("org.mockito:mockito-core:2.+")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junit}")
}
