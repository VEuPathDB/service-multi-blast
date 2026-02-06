import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.*

plugins {
  java
  alias(libs.plugins.kotlin)
  alias(libs.plugins.shadow)
  alias(libs.plugins.vpdb.gradle)
}

// Load Props
val buildProps = Properties()
buildProps.load(FileInputStream(File(rootDir, "service.properties")))
val fullPack = "${buildProps["app.package.root"]}.${buildProps["app.package.service"]}"

kotlin {
  compilerOptions {
    jvmTarget.set(JvmTarget.JVM_25)
  }
}


// configure VEupathDB container plugin
containerService {
  service {
    name = "multi-blast"
    projectPackage = "mb"
  }

  docker {
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
    maven {
      name = "Sonatype Releases"
      url = uri("https://s01.oss.sonatype.org/content/repositories/releases")
    }
  }
}

dependencies {
  implementation(libs.vpdb.hashId)
  implementation(libs.vpdb.containerCore)
  implementation(libs.vpdb.fgpUtil.db)
  implementation(libs.vpdb.blast)
  implementation(libs.vpdb.diamond)
  implementation(libs.vpdb.metrics)
  implementation(libs.vpdb.fireworq)

  implementation(libs.bundles.jersey)
  implementation(libs.bundles.logging)
  implementation(libs.bundles.metrics)
  implementation(libs.bundles.trashLibs)

  implementation(libs.jackson.yaml)
  implementation(libs.friendlyId)
  implementation(libs.pico.lib)
  annotationProcessor(libs.pico.generator)

  testImplementation(libs.bundles.testing)

  testRuntimeOnly(libs.test.junit.engine)
  testRuntimeOnly(libs.test.junit.compat)
  testRuntimeOnly(libs.test.junit.launcher)
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
  ignoreFailures = false
}

val test by tasks.getting(Test::class) {
  // Use junit platform for unit tests
  useJUnitPlatform()
}

tasks.test {
  exclude("org/veupathdb/service/multiblast/generated")
}
