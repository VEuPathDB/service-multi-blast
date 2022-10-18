package mblast.build

import org.gradle.api.Project

fun Project.DockerComposeBuild() {
  with(createProcess(
    "build",
    "--build-arg=GITHUB_USERNAME=" + (properties["gpr.user"] as String? ?: System.getenv("GITHUB_USERNAME")),
    "--build-arg=GITHUB_TOKEN=" + (properties["gpr.key"] as String? ?: System.getenv("GITHUB_TOKEN")),
  ).start()) {
    inputStream.transferTo(System.out)
    errorStream.transferTo(System.err)

    if (waitFor() != 0)
      throw RuntimeException("Failed to build docker compose stack.")
  }
}

fun Project.DockerComposeUp() {
  with(createProcess("up", "--detach").start()) {
    inputStream.transferTo(System.out)
    errorStream.transferTo(System.err)

    if (waitFor() != 0)
      throw RuntimeException("Failed to spin up docker compose stack.")
  }
}

fun Project.DockerComposeStop() {
  with(createProcess("stop").start()) {
    inputStream.transferTo(System.out)
    errorStream.transferTo(System.err)

    if (waitFor() != 0)
      throw RuntimeException("Failed to spin up docker compose stack.")
  }
}

fun Project.DockerComposeDown() {
  with(createProcess("down").start()) {
    inputStream.transferTo(System.out)
    errorStream.transferTo(System.err)

    if (waitFor() != 0)
      throw RuntimeException("Failed to spin up docker compose stack.")
  }
}

private fun Project.createProcess(vararg args: String) =
  ProcessBuilder(
    "docker", "compose",
    "-f", "docker-compose.yml",
    "-f", "docker-compose.dev.yml",
    *args
  )
    .directory(file("service-stack"))



