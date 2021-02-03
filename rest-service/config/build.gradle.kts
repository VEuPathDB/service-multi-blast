plugins {
  java
}

java {
  targetCompatibility = JavaVersion.VERSION_15
  sourceCompatibility = JavaVersion.VERSION_15
}

dependencies {
  implementation(platform(project(":bom")))

  implementation("org.veupathdb.lib", "jaxrs-container-core")
  implementation("info.picocli", "picocli")
}