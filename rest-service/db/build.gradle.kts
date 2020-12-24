plugins {
    java
}

group = "org.veupathdb.service"
version = "1.0.0"

repositories {
  mavenCentral()

}

dependencies {
  implementation("io.vulpine.lib", "sql-import", "0.2.1")
  implementation("io.vulpine.lib", "lib-query-util", "2.1.0")
  implementation("org.veupathdb.lib", "jaxrs-container-core", "3.0.0")
  implementation(files(
    "${rootDir}/vendor/ojdbc8.jar",
    "${rootDir}/vendor/ucp.jar",
    "${rootDir}/vendor/xstreams.jar"
  ))
  implementation("io.vulpine.lib", "Jackfish", "1.+")
}
