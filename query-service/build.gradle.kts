import org.veupathdb.lib.gradle.container.util.Logger.Level

plugins {
  java
  id("org.veupathdb.lib.gradle.container.container-utils") version "4.0.0"
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

// configure VEupathDB container plugin
containerBuild {

  // Change if debugging the build process is necessary.
  logLevel = Level.Info

  // General project level configuration.
  project {

    // Project Name
    name = "demo-service"

    // Project Group
    group = "org.veupathdb.service"

    // Project Version
    version = "1.0.0"

    // Project Root Package
    projectPackage = "org.veupathdb.service.demo"

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
    imageName = "example-service"

  }

  generateJaxRS {
    // List of custom arguments to use in the jax-rs code generation command
    // execution.
    arguments = listOf(/*arg1, arg2, arg3*/)

    // Map of custom environment variables to set for the jax-rs code generation
    // command execution.
    environment = mapOf(/*Pair("env-key", "env-val"), Pair("env-key", "env-val")*/)
  }
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}

tasks.shadowJar {
  exclude("**/Log4j2Plugins.dat")
  archiveFileName.set("service.jar")
}

repositories {
  mavenCentral()
  mavenLocal()
  maven {
    name = "GitHubPackages"
    url  = uri("https://maven.pkg.github.com/veupathdb/maven-packages")
    credentials {
      username = if (extra.has("gpr.user")) extra["gpr.user"] as String? else System.getenv("GITHUB_USERNAME")
      password = if (extra.has("gpr.key")) extra["gpr.key"] as String? else System.getenv("GITHUB_TOKEN")
    }
  }
}

configurations.all {
  resolutionStrategy {
    cacheChangingModulesFor(0, TimeUnit.SECONDS)
    cacheDynamicVersionsFor(0, TimeUnit.SECONDS)
  }
}

dependencies {

  // Required Dependencies
  //
  // These dependencies are required for all projects based on this example
  // repository:

  // Core lib
  implementation("org.veupathdb.lib:jaxrs-container-core:6.9.1")

  // Jersey
  implementation("org.glassfish.jersey.core:jersey-server:3.0.6")

  // Async platform core
  implementation("org.veupathdb.lib:compute-platform:1.0.0")

  // Job IDs
  implementation("org.veupathdb.lib:hash-id:1.1.0")

  // Logging
  implementation("org.slf4j:slf4j-api:1.7.36")
  implementation("org.apache.logging.log4j:log4j-core:2.18.0")
  runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.18.0")


  // Example Dependencies
  //
  // These dependencies were added for the demo/example project and may not be
  // needed

  // Pico CLI
  // Only required if your project adds custom CLI/environment options, see
  // the "MyOptions" class in the demo source code.
  implementation("info.picocli:picocli:4.6.3")

  // Jackson
  // Only required if you are going to be directly using Jackson's JSON api.
  implementation("org.veupathdb.lib:jackson-singleton:3.0.0")

  // Prometheus Metrics Gathering
  // Only required if your project will be doing custom metric reporting outside
  // of the metrics provided by the container core library.
  implementation("io.prometheus:simpleclient:0.16.0")
  implementation("io.prometheus:simpleclient_common:0.16.0")


  // Recommended Dependencies
  //
  // These dependencies are not required, but are recommended.

  // JUnit 5
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

  // Mockito Test Mocking
  testImplementation("org.mockito:mockito-core:4.7.0")
}
