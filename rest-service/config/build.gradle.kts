plugins {
  java
}

group = "org.veupathdb.service"
version = "1.0.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.veupathdb.lib", "jaxrs-container-core", "3.0.0")
  implementation("info.picocli", "picocli", "4.5.2")
}