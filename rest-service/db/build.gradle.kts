plugins {
  java
}

java {
  targetCompatibility = JavaVersion.VERSION_15
  sourceCompatibility = JavaVersion.VERSION_15
}

dependencies {
  implementation("io.vulpine.lib", "sql-import", "0.2.1")
  implementation("io.vulpine.lib", "lib-query-util", "2.1.0")
  implementation("org.veupathdb.lib", "jaxrs-container-core", "4.0.0")
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
  implementation("io.vulpine.lib", "Jackfish", "1.+")
  implementation("org.apache.logging.log4j", "log4j-api", "2.14.0")
  implementation("org.apache.logging.log4j", "log4j-core", "2.14.0")
  implementation("org.apache.logging.log4j", "log4j", "2.14.0")
}
