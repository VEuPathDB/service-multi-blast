/**
 * Initialize the repository for development.
 */
tasks.create("initialize") {
  group = "monorepo"
  doLast {
    // Create blast input directory.  The blastdb directory is required to spin
    // up the service locally.
    file("blastdb").mkdir()

    // Async-Platform based subprojects
    arrayOf(
      "query-service",
      "report-service",
    ).forEach { proj ->
      // Get a handle on the directory containing the subproject.
      val projDir = file(proj)

      // Execute the "install-dev-env" make target in each subproject.
      with(ProcessBuilder("make", "install-dev-env").directory(projDir).start()) {
        errorStream.transferTo(System.err)
        require(waitFor() == 0)
      }
    }
  }
}


/**
 * Dev Docker Compose Stack Build
 *
 * Builds the development docker compose stack images.
 */
tasks.create("dev-compose-build") {
  group = "monorepo"
  doLast {
    with(
      ProcessBuilder(
        "docker", "compose",
        "-f", "docker-compose.dev.yml",
        "build",
        "--build-arg=GITHUB_USERNAME=" + if (extra.has("gpr.user")) extra["gpr.user"] as String? else System.getenv("GITHUB_USERNAME"),
        "--build-arg=GITHUB_TOKEN=" + if (extra.has("gpr.key")) extra["gpr.key"] as String? else System.getenv("GITHUB_TOKEN"),
      )
        .directory(file("docker-compose"))
        .start()
    ) {
      inputStream.transferTo(System.out)
      errorStream.transferTo(System.err)

      if (waitFor() != 0)
        throw RuntimeException("Failed to build docker compose stack.")
    }
  }
}


/**
 * Dev Docker Compose Stack Daemon Run
 *
 * Spins up the development docker compose stack in the background.
 */
tasks.create("dev-compose-up") {
  group = "monorepo"
  doLast {
    with(
      ProcessBuilder(
        "docker", "compose",
        "-f", "docker-compose.dev.yml",
        "up",
        "--detach"
      )
        .directory(file("docker-compose"))
        .start()
    ) {
      inputStream.transferTo(System.out)
      errorStream.transferTo(System.err)

      if (waitFor() != 0)
        throw RuntimeException("Failed to spin up docker compose stack.")
    }
  }
}


/**
 * Dev Docker Compose Stack Stop
 *
 * Stops a running development docker compose stack.
 */
tasks.create("dev-compose-stop") {
  group = "monorepo"
  doLast {
    with(
      ProcessBuilder(
        "docker", "compose",
        "-f", "docker-compose.dev.yml",
        "stop"
      )
        .directory(file("docker-compose"))
        .start()
    ) {
      inputStream.transferTo(System.out)
      errorStream.transferTo(System.err)

      if (waitFor() != 0)
        throw RuntimeException("Failed to stop running docker compose stack.")
    }
  }
}


/**
 * Dev Docker Compose Stack Down
 *
 * Tears down a running development docker compose stack.
 */
tasks.create("dev-compose-down") {
  group = "monorepo"
  doLast {
    with(
      ProcessBuilder(
        "docker", "compose",
        "-f", "docker-compose.dev.yml",
        "down"
      )
        .directory(file("docker-compose"))
        .start()
    ) {
      inputStream.transferTo(System.out)
      errorStream.transferTo(System.err)

      if (waitFor() != 0)
        throw RuntimeException("Failed to tear down docker compose stack.")
    }
  }
}


/**
 * RAML Based HTML Documentation Generation
 *
 * Generates the HTML docs for the RAML in each service and moves the output to
 * the `docs` directory.
 */
tasks.create("raml-gen-docs") {
  group = "monorepo"
  doLast {
    // List of subprojects with APIs
    val subProjects = arrayOf("query-service", "report-service")

    // For each of the defined subprojects
    for (svc in subProjects) {
      // Get a handle on the subproject directory
      val subDir = file(svc)

      // Generate the HTML API docs.
      with(
        ProcessBuilder("make", "raml-gen-docs")
          .directory(subDir)
          .start()
      ) {
        errorStream.transferTo(System.err)
        require(waitFor() == 0)
      }

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
