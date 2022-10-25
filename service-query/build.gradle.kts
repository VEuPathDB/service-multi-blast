import org.veupathdb.lib.gradle.container.util.Logger.Level

plugins {
  java
  id("org.veupathdb.lib.gradle.container.container-utils") version "4.5.2"
  id("com.github.johnrengelman.shadow") version "7.1.2"
  kotlin("jvm")
}

// configure VEupathDB container plugin
containerBuild {

  // Change if debugging the build process is necessary.
  logLevel = Level.Info

  // General project level configuration.
  project {

    // Project Name
    name = "mblast-query-service"

    // Project Group
    group = "org.veupathdb.service"

    // Project Version
    version = "2.0.0"

    // Project Root Package
    projectPackage = "org.veupathdb.service.mblast.query"

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
    imageName = "mblast-query"

  }
}

java {
  targetCompatibility = JavaVersion.VERSION_17
  sourceCompatibility = JavaVersion.VERSION_17
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "17"
  }
}

tasks.shadowJar {
  exclude("**/Log4j2Plugins.dat")
  archiveFileName.set("service.jar")
}

configurations.all {
  resolutionStrategy {
    cacheChangingModulesFor(0, TimeUnit.SECONDS)
    cacheDynamicVersionsFor(0, TimeUnit.SECONDS)
  }
}

dependencies {

  implementation("org.gusdb:fgputil-db:2.7.4")

  // Core lib
  implementation("org.veupathdb.lib:jaxrs-container-core:6.11.0")

  // Jersey
  implementation("org.glassfish.jersey.core:jersey-server:3.0.8")

  // Async platform core
  implementation("org.veupathdb.lib:compute-platform:1.3.3")

  // Job IDs
  implementation("org.veupathdb.lib:hash-id:1.1.0")

  // Logging
  implementation("org.apache.logging.log4j:log4j-core:2.19.0")
  runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.19.0")


  implementation("org.veupathdb.lib:jackson-singleton:3.0.1")

  implementation("io.prometheus:simpleclient:0.16.0")
  implementation("io.prometheus:simpleclient_common:0.16.0")

  implementation("io.foxcapades.lib:env-access:1.0.0")
  implementation("io.foxcapades.lib:md5:1.0.0")

  implementation("org.veupathdb.lib:blast-types:8.2.0")
  implementation(project(":lib-blast-query-parser"))
  implementation(project(":lib-mblast-utils"))
  implementation(project(":lib-temp-cache"))

  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
  testImplementation("org.mockito:mockito-core:4.8.0")
}
