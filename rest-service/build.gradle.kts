import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import java.io.FileInputStream
import java.util.*

plugins {
  java
}

// Load Props
val buildProps = Properties()
buildProps.load(FileInputStream(File(rootDir, "service.properties")))
val fullPack = "${buildProps["app.package.root"]}.${buildProps["app.package.service"]}"

java {
  targetCompatibility = JavaVersion.VERSION_15
  sourceCompatibility = JavaVersion.VERSION_15
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
      url  = uri("https://maven.pkg.github.com/veupathdb/maven-packages")
      credentials {
        username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
        password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
      }
    }
  }
}
dependencies {
  val junit = "5.7.0"

  implementation(platform(project(":bom")))

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
  runtimeOnly("org.apache.commons:commons-dbcp2")
  runtimeOnly("org.json:json")
  runtimeOnly("com.fasterxml.jackson.datatype:jackson-datatype-json-org")
  implementation("com.fasterxml.jackson.module:jackson-module-parameter-names")
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

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
  implementation("org.veupathdb.lib:jaxrs-container-core")
  implementation("org.veupathdb.lib:java-blast")

  // Jersey
  implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http")
  implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-servlet")
  implementation("org.glassfish.jersey.media:jersey-media-json-jackson")
  implementation("org.glassfish.jersey.media:jersey-media-multipart")
  implementation("jakarta.ws.rs:jakarta.ws.rs-api")
  runtimeOnly("org.glassfish.jersey.inject:jersey-hk2")

  // Jackson
  implementation("com.fasterxml.jackson.core:jackson-databind")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")

  // Log4J
  implementation("org.apache.logging.log4j:log4j-api")
  implementation("org.apache.logging.log4j:log4j-core")
  implementation("org.apache.logging.log4j:log4j")

  // Metrics
  implementation("io.prometheus:simpleclient")
  implementation("io.prometheus:simpleclient_common")

  // Utils
  implementation("io.vulpine.lib", "sql-import")
  implementation("io.vulpine.lib", "lib-query-util")
  implementation("io.vulpine.lib:Jackfish")
  implementation("io.vulpine.lib:iffy")
  implementation("com.devskiller.friendly-id:friendly-id")
  implementation("info.picocli:picocli:4.6.1")
  annotationProcessor("info.picocli:picocli-codegen:4.6.1")

  // Unit Testing
  testImplementation("org.junit.jupiter:junit-jupiter-api:${junit}")
  testImplementation("org.junit.jupiter:junit-jupiter-params:${junit}")
  testImplementation("org.mockito:mockito-core:2.+")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junit}")
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
