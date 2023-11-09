plugins {
  java
  id("org.veupathdb.lib.gradle.container.container-utils") version "5.0.0"
  id("com.github.johnrengelman.shadow") version "7.1.2"
  kotlin("jvm")
}

containerBuild {

  project {
    name = "mblast-query-service"
    group = "org.veupathdb.service"
    version = "2.0.0"
    projectPackage = "org.veupathdb.service.mblast.query"
    mainClassName = "Main"
  }

  docker {
    imageName = "mblast-query"
  }
}

java {
  targetCompatibility = JavaVersion.VERSION_19
  sourceCompatibility = JavaVersion.VERSION_19
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "19"
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
  implementation("org.gusdb:fgputil-db:2.12.11-jakarta")

  implementation("org.veupathdb.lib:jaxrs-container-core:6.19.1")
  implementation("org.veupathdb.lib:compute-platform:1.5.3")
  implementation("org.veupathdb.lib:hash-id:1.1.0")
  implementation("org.veupathdb.lib:jackson-singleton:3.1.0")

  implementation(project(":blast-types"))
  implementation(project(":lib-blast-query-parser"))
  implementation(project(":lib-mblast-utils"))
  implementation(project(":lib-temp-cache"))

  implementation("org.glassfish.jersey.core:jersey-server:3.1.3")

  implementation("org.apache.logging.log4j:log4j-core:2.21.1")
  runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.21.1")


  implementation("io.prometheus:simpleclient:0.16.0")
  implementation("io.prometheus:simpleclient_common:0.16.0")

  implementation("io.foxcapades.lib:env-access:1.0.0")
  implementation("io.foxcapades.lib:md5:1.0.1")

  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
  testImplementation("org.mockito:mockito-core:4.8.0")
}
