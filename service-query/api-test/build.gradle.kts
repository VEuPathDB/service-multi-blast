import mblast.build.ANSI

plugins {
  kotlin("jvm")
}

dependencies {
  testImplementation("org.veupathdb.lib:jackson-singleton:3.0.1")

  testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter:5.9.0")

  testImplementation("io.rest-assured:rest-assured:5.2.0")
}

tasks.withType<Test> {
  doFirst {
    with(file("test.properties")) {
      if (!exists()) {
        createNewFile()
        bufferedWriter().use {
          it.write("header.auth-key=")
          it.newLine()
        }

        println(ANSI.fgBit(203u))
        println("\nPlease edit the test.properties file and try again.")
        println(ANSI.RESET)

        throw RuntimeException()
      }
    }
  }

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
