plugins {
  java
}

java {
  targetCompatibility = JavaVersion.VERSION_15
  sourceCompatibility = JavaVersion.VERSION_15
}

dependencies {
  implementation(platform(project(":bom")))

  implementation(files(
    "${rootDir}/vendor/fgputil-accountdb-1.0.0.jar",
    "${rootDir}/vendor/fgputil-core-1.0.0.jar",
    "${rootDir}/vendor/fgputil-db-1.0.0.jar",
    "${rootDir}/vendor/fgputil-web-1.0.0.jar"
  ))
  implementation(files(
    "${rootDir}/vendor/ojdbc8.jar",
    "${rootDir}/vendor/ucp.jar",
    "${rootDir}/vendor/xstreams.jar"
  ))

  implementation("io.vulpine.lib", "sql-import")
  implementation("io.vulpine.lib", "lib-query-util")
  implementation("io.vulpine.lib", "Jackfish")

  implementation("org.veupathdb.lib", "jaxrs-container-core")

  implementation("org.apache.logging.log4j", "log4j-api")
  implementation("org.apache.logging.log4j", "log4j-core")
  implementation("org.apache.logging.log4j", "log4j")
}
