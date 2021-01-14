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
  implementation("org.veupathdb.lib", "jaxrs-container-core", "4.0.0")
  implementation("com.fasterxml.jackson.core", "jackson-databind", "2.12.1")
}
