import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  id("org.jetbrains.dokka") version "1.7.20"
  `maven-publish`
}

group = "org.veupathdb.lib"
version = "1.0.0"

java {
  targetCompatibility = JavaVersion.VERSION_16
  sourceCompatibility = JavaVersion.VERSION_16

  withSourcesJar()
  withJavadocJar()
}

tasks.dokkaHtml {
  outputDirectory.set(rootDir.resolve("docs/dokka/mblast-sdk/$version"))
}


dependencies {
  api("com.fasterxml.jackson.core:jackson-databind:2.14.0")
  api("com.fasterxml.jackson.datatype:jackson-datatype-json-org:2.14.0")
  api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.0")
  api("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.0")
  api("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0")
  api("com.fasterxml.jackson.module:jackson-module-parameter-names:2.14.0")

  api("org.veupathdb.lib:hash-id:1.1.0")

  implementation("io.foxcapades.lib:k-multipart:1.0.0")

  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
  testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.jar {
  manifest {
    attributes["Implementation-Title"] = project.name
    attributes["Implementation-Version"] = project.version
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "16"
    freeCompilerArgs = listOf("-Xjvm-default=all")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events.addAll(listOf(org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
      org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
      org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT,
      org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR,
      org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED))

    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    showExceptions = true
    showCauses = true
    showStackTraces = true
    showStandardStreams = true
    enableAssertions = true
  }
  ignoreFailures = true // Always try to run all tests for all modules
}


publishing {
  repositories {
    maven {
      name = "GitHub"

      url = uri("https://maven.pkg.github.com/veupathdb/service-multi-blast")

      credentials {
        username = rootProject.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
        password = rootProject.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
      }
    }
  }

  publications {
    create<MavenPublication>("gpr") {
      from(components["java"])
      pom {
        name.set("Multi-Blast Service API SDK")
        description.set("A small SDK providing programatic access to the Multi-Blast REST APIs.")
        url.set("https://github.com/VEuPathDB/service-multi-blast")
        developers {
          developer {
            id.set("epharper")
            name.set("Elizabeth Paige Harper")
            email.set("epharper@upenn.edu")
            url.set("https://github.com/foxcapades")
            organization.set("VEuPathDB")
          }
        }
        scm {
          connection.set("scm:git:git://github.com/VEuPathDB/lib-java-blast.git")
          developerConnection.set("scm:git:ssh://github.com/VEuPathDB/lib-java-blast.git")
          url.set("https://github.com/VEuPathDB/service-multi-blast")
        }
      }
    }
  }
}
