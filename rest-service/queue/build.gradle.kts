plugins {
  java
}

java {
  targetCompatibility = JavaVersion.VERSION_15
  sourceCompatibility = JavaVersion.VERSION_15
}

dependencies {
  implementation(platform(project(":bom")))

  implementation(project(":config"))

  implementation("org.veupathdb.lib", "jaxrs-container-core")
  implementation("com.fasterxml.jackson.core", "jackson-databind")
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

  implementation("org.apache.logging.log4j", "log4j-api")
  implementation("org.apache.logging.log4j", "log4j-core")
}