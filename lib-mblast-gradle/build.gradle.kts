plugins {
  kotlin("jvm") version "1.7.20"
}

group = "mblast"
version = '0'

repositories {
  mavenCentral()
}

dependencies {
  implementation(gradleApi())
  implementation("com.hierynomus:sshj:0.34.0")
}