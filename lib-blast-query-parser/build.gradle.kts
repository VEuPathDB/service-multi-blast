plugins {
  kotlin("jvm") version "1.7.20"
  id("org.jetbrains.dokka") version "1.7.10"
  `maven-publish`
}

group = "org.veupathdb.lib"
version = "1.0-SNAPSHOT"

dependencies {
  implementation("org.veupathdb.lib.mblast:mblast-utils:1.0-SNAPSHOT") { isChanging = true }
}

java {
  targetCompatibility = JavaVersion.VERSION_17
  sourceCompatibility = JavaVersion.VERSION_17

  withSourcesJar()
  withJavadocJar()
}

tasks.jar {
  manifest {
    attributes["Implementation-Title"] = project.name
    attributes["Implementation-Version"] = project.version
  }
}

tasks.dokkaHtml {
  outputDirectory.set(rootDir.resolve("docs/dokka/mblast-utils/$version"))
}

tasks.dokkaJavadoc {
  outputDirectory.set(rootDir.resolve("docs/javadoc/mblast-utils/$version"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "17"
  }
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
        name.set("BLAST Query Parser")
        description.set("Provides tools for working with BLAST queries.")
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
          connection.set("scm:git:git://github.com/VEuPathDB/service-multi-blast.git")
          developerConnection.set("scm:git:ssh://github.com/VEuPathDB/service-multi-blast.git")
          url.set("https://github.com/VEuPathDB/service-multi-blast")
        }
      }
    }
  }
}