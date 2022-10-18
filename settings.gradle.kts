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

include(":service-query")
project(":service-query").name = "query-service"
include(":service-query:api-test")
project(":service-query:api-test").name = "query-api-test"

include(":service-report")
project(":service-report").name = "report-service"

include("common:blast-query-parser")
include("common:blast-types")
include("common:mblast-utils")
include("common:temp-cache")

