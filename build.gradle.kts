/**
 * Dev Docker Compose Stack Build
 *
 * Builds the development docker compose stack images.
 */
tasks.create("dev-compose-build") {
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
  doLast {
    for (svc in arrayOf("query-service", "report-service")) {
      with(
        ProcessBuilder("./gradlew", "generate-raml-docs")
          .directory(file(svc))
          .start()
      ) {
        errorStream.transferTo(System.err)
        require(waitFor() == 0)
      }

      val docDir = file("docs/$svc")

      docDir.mkdirs()

      with(file("$svc/docs/api.html")) {
        copyTo(file("docs/$svc/api.html"))
        delete()
      }
    }
  }
}