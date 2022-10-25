package api.test.support

import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.isDirectory
import kotlin.io.path.isRegularFile
import kotlin.io.path.name
import kotlin.random.Random

object BlastTargets {
  private val projects = HashMap<String, Project>()

  init {
    Files.list(Path.of(System.getProperty("user.dir")).parent.parent.resolve("blastdb"))
      .filter { it.isDirectory() }
      .map { Intermediate1(it.name, it) }
      .flatMap { (name, path) ->
        Files.list(path)
          .filter { it.isDirectory() }
          .map { Intermediate2(name, it.name, it) } }
      .flatMap { (name, build, path) ->
        Files.list(path)
          .filter { it.isDirectory() }
          .map { Intermediate3(name, build, it.name, it) } }
      .flatMap { (name, build, organism, path) ->
        Files.list(path.resolve("blast"))
          .filter { it.isRegularFile() }
          .map { it.name }
          .filter { it.endsWith("in") }
          .map { Intermediate4(name, build, organism, it.substring(0, it.length-4), it.endsWith(".nin")) } }
      .forEach { (site, build, organism, db, isNA) ->
        projects.computeIfAbsent(site) { Project(site) }
          .children.computeIfAbsent(build) { Build(build) }
          .children.computeIfAbsent(organism) { Organism(organism) }
          .children.computeIfAbsent(db) { Target(db, isNA) }
      }
  }

  @JvmStatic
  fun randomTarget(isNA: Boolean): BlastTarget {
    val possibilities = ArrayList<BlastTarget>()

    projects.values.forEach { project ->
      project.children.values.forEach { build ->
        build.children.values.forEach { organism ->
          organism.children.values.forEach {
            if (it.isNA == isNA)
              possibilities.add(BlastTarget(project.name, organism.name, it.name)) } } } }

    return possibilities[Random(System.currentTimeMillis()).nextInt(possibilities.size)]
  }

  private data class Intermediate1(val site: String, val path: Path)
  private data class Intermediate2(val site: String, val build: String, val path: Path)
  private data class Intermediate3(val site: String, val build: String, val organism: String, val path: Path)
  private data class Intermediate4(val site: String, val build: String, val organism: String, val db: String, val isNA: Boolean)

  private data class Project(val name: String) {
    val children = HashMap<String, Build>()
  }

  private data class Build(val name: String) {
    val children = HashMap<String, Organism>()
  }

  private data class Organism(val name: String) {
    val children = HashMap<String, Target>()
  }

  private data class Target(val name: String, val isNA: Boolean)

  data class BlastTarget(val site: String, val organism: String, val target: String)
}