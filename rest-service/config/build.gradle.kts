plugins {
  java
}

java {
  targetCompatibility = JavaVersion.VERSION_15
  sourceCompatibility = JavaVersion.VERSION_15
}

dependencies {
  implementation("org.veupathdb.lib", "jaxrs-container-core", "4.0.0")
  implementation("info.picocli", "picocli", "4.5.2")
}