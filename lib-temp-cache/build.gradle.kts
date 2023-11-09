plugins {
  kotlin("jvm")
  id("org.jetbrains.dokka") version "1.9.10"
}

java {
  targetCompatibility = JavaVersion.VERSION_1_8
  sourceCompatibility = JavaVersion.VERSION_1_8

  withSourcesJar()
  withJavadocJar()
}

dependencies {
  implementation("org.slf4j:slf4j-api:1.7.36")
}

tasks.dokkaHtml {
  outputDirectory.set(rootDir.resolve("docs/dokka/temp-cache/$version"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
  }
}
