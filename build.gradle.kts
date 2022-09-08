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
        throw RuntimeException("Failed to spin up docker compose stack.")
    }
  }
}