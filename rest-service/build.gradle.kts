import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.util.*

plugins {
  java
  id("org.veupathdb.lib.gradle.container.container-utils") version "1.4.0"
  kotlin("jvm") version "1.6.0"
}

// Load Props
val buildProps = Properties()
buildProps.load(FileInputStream(File(rootDir, "service.properties")))
val fullPack = "${buildProps["app.package.root"]}.${buildProps["app.package.service"]}"

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(16))
  }
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = "16"
  }
}
containerBuild {
  fgpUtilVersion = "14aa44a13c28257b702a98ddbecdf1e72812e2e6"
}

// Project settings
allprojects {
  group = buildProps["project.group"] ?: error("empty 1")
  version = buildProps["project.version"] ?: error("empty 2")

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
}

dependencies {
  val junit = "5.7.0"

  implementation(kotlin("stdlib-jdk8"))

  //
  // FgpUtil & Compatibility Dependencies
  //

  // FgpUtil jars
  implementation(files(
    "${rootProject.projectDir}/vendor/fgputil-accountdb-1.0.0.jar",
    "${rootProject.projectDir}/vendor/fgputil-core-1.0.0.jar",
    "${rootProject.projectDir}/vendor/fgputil-db-1.0.0.jar",
    "${rootProject.projectDir}/vendor/fgputil-web-1.0.0.jar"
  ))

  // Compatibility bridge to support the long dead log4j-1.X
  runtimeOnly("org.apache.logging.log4j:log4j-1.2-api")

  // Extra FgpUtil dependencies
  runtimeOnly("org.apache.commons:commons-dbcp2:2.8.0")
  runtimeOnly("org.json:json:20211205")
  runtimeOnly("com.fasterxml.jackson.datatype:jackson-datatype-json-org:2.13.0")
  implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.13.0")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.13.0")
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.0")

  //
  // Project Dependencies
  //

  // Oracle
  runtimeOnly(files(
    "${rootProject.projectDir}/vendor/ojdbc8.jar",
    "${rootProject.projectDir}/vendor/ucp.jar",
    "${rootProject.projectDir}/vendor/xstreams.jar"
  ))


  // Core lib, prefers local checkout if available
  implementation("org.veupathdb.lib:jaxrs-container-core:6.0.0")
  implementation("org.veupathdb.lib:java-blast:5.0.8")

  // Jersey
  implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http:3.0.3")
  implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-servlet:3.0.3")
  implementation("org.glassfish.jersey.media:jersey-media-json-jackson:3.0.3")
  implementation("org.glassfish.jersey.media:jersey-media-multipart:3.0.3")
  implementation("jakarta.platform:jakarta.jakartaee-web-api:9.1.0")
  runtimeOnly("org.glassfish.jersey.inject:jersey-hk2:3.0.3")

  // Jackson
  implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.0")

  // Log4J
  implementation("org.apache.logging.log4j:log4j-api:2.16.0")
  implementation("org.apache.logging.log4j:log4j-core:2.16.0")
  implementation("org.apache.logging.log4j:log4j:2.14.0")

  // Metrics
  implementation("io.prometheus:simpleclient:0.13.0")
  implementation("io.prometheus:simpleclient_common:0.13.0")

  // Utils
  implementation("io.vulpine.lib", "sql-import", "0.2.1")
  implementation("io.vulpine.lib", "lib-query-util", "2.1.0")
  implementation("io.vulpine.lib:Jackfish:1.1.0")
  implementation("io.vulpine.lib:iffy:1.0.1")
  implementation("com.devskiller.friendly-id:friendly-id:1.1.0")
  implementation("info.picocli:picocli:4.6.2")
  annotationProcessor("info.picocli:picocli-codegen:4.6.2")

  // Unit Testing
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
  testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
  testImplementation("org.mockito:mockito-core:4.1.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.compileJava {
  options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
}

tasks.jar {

  duplicatesStrategy = DuplicatesStrategy.EXCLUDE

  manifest {
    attributes["Main-Class"] = "mb.Main"
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
}
