plugins {
  kotlin("jvm")
  id("org.jetbrains.dokka") version "1.7.10"
  `maven-publish`
}

group = "org.veupathdb.lib"
version = "1.0-SNAPSHOT"

dependencies {
  implementation("org.veupathdb.lib.mblast:mblast-utils:1.0-SNAPSHOT") { isChanging = true }
}

java {
  targetCompatibility = JavaVersion.VERSION_17
  sourceCompatibility = JavaVersion.VERSION_17

  withSourcesJar()
  withJavadocJar()
}

tasks.jar {
  manifest {
    attributes["Implementation-Title"] = project.name
    attributes["Implementation-Version"] = project.version
  }
}

tasks.dokkaHtml {
  outputDirectory.set(rootDir.resolve("docs/dokka/mblast-utils/$version"))
}

tasks.dokkaJavadoc {
  outputDirectory.set(rootDir.resolve("docs/javadoc/mblast-utils/$version"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "17"
  }
}
