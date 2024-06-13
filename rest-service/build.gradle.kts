import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.util.*

plugins {
  java
  id("org.veupathdb.lib.gradle.container.container-utils") version "4.8.10"
  id("com.github.johnrengelman.shadow") version "7.1.2"
  kotlin("jvm") version "1.9.0"
}

// Load Props
val buildProps = Properties()
buildProps.load(FileInputStream(File(rootDir, "service.properties")))
val fullPack = "${buildProps["app.package.root"]}.${buildProps["app.package.service"]}"

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = "17"
  }
}

// configure VEupathDB container plugin
containerBuild {

  // Change if debugging the build process is necessary.
  logLevel = org.veupathdb.lib.gradle.container.util.Logger.Level.Info

  // General project level configuration.
  project {

    // Project Name
    name = "multi-blast"

    // Project Group
    group = "org.veupathdb.service"

    // Project Version
    version = "1.0.0"

    // Project Root Package
    projectPackage = "mb"

    // Main Class Name
    mainClassName = "Main"
  }

  // Docker build configuration.
  docker {

    // Docker build context
    context = "."

    // Name of the target docker file
    dockerFile = "Dockerfile"

    // Resulting image tag
    imageName = "multi-blast"

  }

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
  implementation(kotlin("stdlib"))
  implementation(kotlin("stdlib-jdk8"))
  implementation("org.veupathdb.lib:hash-id:1.0.2")

  //
  // Project Dependencies
  //

  implementation("org.veupathdb.lib:jaxrs-container-core:7.1.3")
  implementation("org.gusdb:fgputil-db:2.14.1-jakarta")
  implementation("org.veupathdb.lib:java-blast:5.0.9")

  // Jersey
  implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http:3.1.1")
  implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-servlet:3.1.1")
  implementation("org.glassfish.jersey.media:jersey-media-json-jackson:3.1.1")
  implementation("org.glassfish.jersey.media:jersey-media-multipart:3.1.1")
  runtimeOnly("org.glassfish.jersey.inject:jersey-hk2:3.1.1")

  // Jackson
  implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.3")

  // Log4J
  implementation("org.apache.logging.log4j:log4j-api:2.20")
  implementation("org.apache.logging.log4j:log4j-core:2.20")

  // Metrics
  implementation("io.prometheus:simpleclient:0.16.0")
  implementation("io.prometheus:simpleclient_common:0.16.0")

  // Utils
  implementation("io.vulpine.lib", "sql-import", "0.2.1")
  implementation("io.vulpine.lib", "lib-query-util", "2.1.0")
  implementation("io.vulpine.lib:Jackfish:1.1.0")
  implementation("io.vulpine.lib:iffy:1.0.1")
  implementation("com.devskiller.friendly-id:friendly-id:1.1.0")
  implementation("info.picocli:picocli:4.7.3")
  annotationProcessor("info.picocli:picocli-codegen:4.7.3")

  implementation("org.veupathdb.lib:lib-prometheus-stats:1.1.0")
  implementation("org.veupathdb.lib:jvm-fireworq:1.0.3")
  implementation("org.veupathdb.lib:jackson-singleton:2.1.0")

  // Unit Testing
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
  testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
  testImplementation("org.mockito:mockito-core:5.2.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks.compileJava {
  options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
}

tasks.shadowJar {
  archiveBaseName.set("service")
  archiveClassifier.set("")
  archiveVersion.set("")

  exclude("**/Log4j2Plugins.dat")
}

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
