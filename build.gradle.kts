import mblast.build.*

allprojects {
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


/**
 * Initialize the repository for development.
 *
 * This task is a catch-all for any and all tasks that need to be performed
 * before local development can successfully begin.
 *
 * Each step should be commented with what it's doing and why.
 */
tasks.create("download-blast-dbs") {
  group = "monorepo"
  doLast { CreateBlastDBDirectoryIfNotExists() }
}


/**
 * Dev Docker Compose Stack Build
 *
 * Builds the development docker compose stack images.
 */
tasks.create("compose-build") {
  group = "monorepo"
  doLast { DockerComposeBuild() }
}


/**
 * Dev Docker Compose Stack Daemon Run
 *
 * Spins up the development docker compose stack in the background.
 */
tasks.create("compose-up") {
  group = "monorepo"
  dependsOn("download-blast-dbs")
  doLast { DockerComposeUp() }
}


/**
 * Dev Docker Compose Stack Stop
 *
 * Stops a running development docker compose stack.
 */
tasks.create("compose-stop") {
  group = "monorepo"
  doLast { DockerComposeStop() }
}


/**
 * Dev Docker Compose Stack Down
 *
 * Tears down a running development docker compose stack.
 */
tasks.create("compose-down") {
  group = "monorepo"
  doLast { DockerComposeDown() }
}


/**
 * RAML Based HTML Documentation Generation
 *
 * Generates the HTML docs for the RAML in each service and moves the output to
 * the `docs` directory.
 *
 * **NOTE**: This task overrides the `generate-raml-docs` task in the child
 * projects.
 */
tasks.create("generate-raml-docs") {
  group = "monorepo"

  dependsOn(
    ":query-service:generate-raml-docs",
    ":report-service:generate-raml-docs",
  )

  doLast {
    // List of subprojects with APIs
    val subProjects = arrayOf("query-service", "report-service")

    // For each of the defined subprojects
    for (svc in subProjects) {
          // Ensure the docs directory exists
      with(file("docs/$svc"), File::mkdirs)

      // Copy the generated HTML file to the docs directory.
      with(file("$svc/docs/api.html")) {
        copyTo(file("docs/$svc/api.html"), true)
        delete()
      }
    }
  }
}

tasks.create("generate-env-file") {
  group = "monorepo"

  data class EnvVar(val key: String, val default: String = "")

  val pat = Regex("\\$\\{(\\w+)(?::(?:\\-(.+?)|\\?))?\\}")

  doLast {
    val lines = file("stack-mblast")
      .listFiles()
      .filter { it.name.endsWith(".yml") }
      .stream()
      .map { it.readLines() }
      .flatMap { it.stream() }
      .filter { it.contains("\${") }
      .map { pat.find(it) }
      .filter { it != null }
      .map { it!!.groupValues }
      .collect({ HashMap<String, String>(64) }, { m, e -> m[e[1]] = e[2] }, { a, b -> a.putAll(b) })
      .entries
      .stream()
      .map { (k, v) -> "$k=$v" }
      .sorted()
      .toList()

    file("stack-mblast/sample.env").bufferedWriter().use { writer ->
      lines.forEach {
        writer.write(it)
        writer.newLine()
      }
    }

    println("\u001b[38:5:203m")
    println("""
      Generated file "stack-mblast/sample.env".
      
      Please edit this file with the correct values and rename it to just \".env\" for it to be automatically picked up
      by docker compose.
    """.trimIndent())
    println("\u001b[0m")
  }
}