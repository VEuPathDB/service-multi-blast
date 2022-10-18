plugins {
  kotlin("jvm") version "1.7.20"
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
  mavenLocal()
  mavenCentral()
  maven {
    name = "GitHubPackages"
    url  = uri("https://maven.pkg.github.com/veupathdb/packages")
    credentials {
      username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
      password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
    }
  }
}

dependencies {
  implementation("org.veupathdb.lib:blast-types:8.0.0")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
  archiveFileName.set("printing-help.jar")
}

tasks.withType<Jar> {
  manifest {
    attributes["Main-Class"] = "example.MainKt"
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
  }
}
