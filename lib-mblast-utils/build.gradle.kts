plugins {
  kotlin("jvm")
  id("org.jetbrains.dokka") version "1.7.10"
}

repositories {
  mavenCentral()
}

java {
  targetCompatibility = JavaVersion.VERSION_1_8
  sourceCompatibility = JavaVersion.VERSION_1_8

  withSourcesJar()
  withJavadocJar()
}

tasks.dokkaHtml {
  outputDirectory.set(rootDir.resolve("docs/dokka/mblast-utils/$version"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation("io.foxcapades.lib.kps:kpd:1.1.0")
}