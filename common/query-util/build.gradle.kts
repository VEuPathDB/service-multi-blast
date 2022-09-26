plugins {
  kotlin("jvm") version "1.7.10"
}

group = "org.veupathdb.mblast"
version = "unspecified"

repositories {
  mavenCentral()
}

dependencies {
  implementation("io.foxcapades.lib.kps:kpd:1.1.0")

  testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
  useJUnitPlatform()
}