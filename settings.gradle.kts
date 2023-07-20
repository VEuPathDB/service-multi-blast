pluginManagement {
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    maven {
      name = "GitHubPackages"
      url  = uri("https://maven.pkg.github.com/veupathdb/maven-packages")
      credentials {
        username = if (extra.has("gpr.user")) extra["gpr.user"] as String? else System.getenv("GITHUB_USERNAME")
        password = if (extra.has("gpr.key")) extra["gpr.key"] as String? else System.getenv("GITHUB_TOKEN")
      }
    }
  }
}

rootProject.name = "mblast"

includeBuild("lib-mblast-gradle")

include(":service-query")
project(":service-query").name = "query-service"

include(":service-report")
project(":service-report").name = "report-service"

include(":lib-blast-types")
project(":lib-blast-types").name = "blast-types"

include(":lib-mblast-utils")
include(":lib-temp-cache")
include(":lib-blast-query-parser")

include(":lib-jvm-mblast-sdk")
project(":lib-jvm-mblast-sdk").name = "mblast-sdk"

include(":script-mblast2-migration")
include(":script-mblast-e2e")
