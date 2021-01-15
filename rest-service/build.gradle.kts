import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import java.io.FileInputStream
import java.util.*

plugins {
  java
  jacoco
}

apply(from = "dependencies.gradle.kts")

// Load Props
val buildProps = Properties()
buildProps.load(FileInputStream(File(rootDir, "service.properties")))
val fullPack = "${buildProps["app.package.root"]}.${buildProps["app.package.service"]}"

java {
  targetCompatibility = JavaVersion.VERSION_15
  sourceCompatibility = JavaVersion.VERSION_15
}

// Project settings
group = buildProps["project.group"] ?: error("empty 1")
version = buildProps["project.version"] ?: error("empty 2")

allprojects {
  repositories {
    jcenter()
    mavenCentral()
  }

}

tasks.jar {
  manifest {
    attributes["Main-Class"] = "${fullPack}.${buildProps["app.main-class"]}"
    attributes["Implementation-Title"] = buildProps["project.name"]
    attributes["Implementation-Version"] = buildProps["project.version"]
  }
  println("Packaging Components")
  from(configurations.runtimeClasspath.get().map {
    println("  " + it.name)

    if (it.isDirectory) it else zipTree(it).matching {
      exclude { f ->
        val name = f.name.toLowerCase()
        (name.contains("log4j") && name.contains(".dat")) ||
          name.endsWith(".sf") ||
          name.endsWith(".dsa") ||
          name.endsWith(".rsa")
      } } })
  archiveFileName.set("service.jar")
}

tasks.register("print-package") { print(fullPack) }
tasks.register("print-container-name") { print(buildProps["container.name"]) }

tasks.withType<Test> {
    testLogging {
      events.addAll(listOf(TestLogEvent.FAILED,
        TestLogEvent.SKIPPED,
        TestLogEvent.STANDARD_OUT,
        TestLogEvent.STANDARD_ERROR,
        TestLogEvent.PASSED))

      exceptionFormat = TestExceptionFormat.FULL
      showExceptions = true
      showCauses = true
      showStackTraces = true
      showStandardStreams = true
      enableAssertions = true
  }
  ignoreFailures = true // Always try to run all tests for all modules
}

val test by tasks.getting(Test::class) {
  // Use junit platform for unit tests
  useJUnitPlatform()
}

tasks.test {
  exclude("org/veupathdb/service/multiblast/generated")
  finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
  dependsOn(tasks.test)
  reports {
    xml.isEnabled = true
    csv.isEnabled = false
    html.isEnabled = true
  }
}
jacoco {
  toolVersion = "0.8.6"
}
