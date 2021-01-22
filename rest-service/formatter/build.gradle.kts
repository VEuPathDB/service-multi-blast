plugins {
  java
}

java {
  targetCompatibility = JavaVersion.VERSION_15
  sourceCompatibility = JavaVersion.VERSION_15
}

dependencies {
  implementation(project(":config"))
  implementation("org.veupathdb.lib", "jaxrs-container-core", "4.0.0")
  implementation("com.fasterxml.jackson.core", "jackson-databind", "2.12.1")
  implementation("org.apache.logging.log4j", "log4j-api", "2.14.0")
  implementation("org.apache.logging.log4j", "log4j-core", "2.14.0")
  implementation("org.apache.logging.log4j", "log4j", "2.14.0")
}
