plugins {
  java
}

group = "org.veupathdb.service"
version = "1.0.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation(project(":config"))
  implementation("org.veupathdb.lib", "jaxrs-container-core", "3.0.0")
  implementation("com.fasterxml.jackson.core", "jackson-databind", "2.+")
  implementation("org.apache.logging.log4j", "log4j-api", "2.14.0")
  implementation("org.apache.logging.log4j", "log4j-core", "2.14.0")
}