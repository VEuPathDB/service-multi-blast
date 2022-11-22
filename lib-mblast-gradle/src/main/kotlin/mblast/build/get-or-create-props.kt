@file:Suppress("NOTHING_TO_INLINE")

package mblast.build

import org.gradle.api.Project
import java.io.File
import java.util.*

private const val KEY_SSH_USER = "ssh.user"
private const val KEY_SSH_HOST = "ssh.host"
private const val KEY_SSH_PORT = "ssh.port"
private const val KEY_SSH_PATH = "ssh.key.path"
private const val KEY_SSH_PASS = "ssh.key.pass"
private const val KEY_SITE_NAME = "site.name"
private const val KEY_SITE_BUILD = "site.build"
private const val KEY_REMOTE_DIR = "files.root"

private fun makePropsContent() = """
$KEY_SSH_USER=${System.getProperty("user.name")}
$KEY_SSH_HOST=some.ssh.server.org
$KEY_SSH_PORT=21
$KEY_SSH_PATH=${System.getProperty("user.home")}/.ssh/id_rsa
$KEY_SSH_PASS=blank-for-no-password

$KEY_SITE_NAME=PlasmoDB
$KEY_SITE_BUILD=build-59

$KEY_REMOTE_DIR=/var/www/Common/apiSiteFilesMirror/webServices
"""

/**
 * Get Dev Props or Create and Fail
 *
 * This function attempts to load the `mblast-dev.properties` file from the
 * project root directory.
 *
 * If the file exists it is parsed and returned.
 *
 * If the file does not exist, it will be created and this function will throw
 * an exception.
 *
 * @return Parsed properties.
 *
 * @throws RuntimeException If the dev properties file did not exist.
 */
internal fun Project.getPropsOrCreateAndFail(): DevProps {
  val propFile = file("mblast-dev.properties")

  propFile.exists() && return parseMBlastProps(propFile)

  propFile.createNewFile()
  propFile.bufferedWriter().use {
    it.write(makePropsContent())
    it.newLine()
    it.flush()
  }

  println(ANSI.fgBit(206u))
  println("\nMissing required properties file `mblast-dev.properties`.")
  println(ANSI.fgBit(30u))
  println("\nFile created, please edit this file with the correct configuration values and rerun this task.")
  println(ANSI.RESET)

  throw RuntimeException()
}

internal data class DevProps(
  val sshUser: String,
  val sshHost: String,
  val sshPort: Int,
  val sshPath: String,
  val sshPass: String,

  val siteName:  String,
  val siteBuild: String,

  val filesRoot: String,
)


private fun parseMBlastProps(file: File) = Properties().let { props ->
  file.bufferedReader().use { props.load(it) }

  DevProps(
    props.requireString(KEY_SSH_USER),
    props.requireString(KEY_SSH_HOST),
    props.requireInt(KEY_SSH_PORT),
    props.requireString(KEY_SSH_PATH),
    props.optionalString(KEY_SSH_PASS),
    props.requireString(KEY_SITE_NAME),
    props.requireString(KEY_SITE_BUILD),
    props.requireString(KEY_REMOTE_DIR),
  )
}

private inline fun Properties.optionalString(key: String) =
  (this[key] as String?) ?: ""

private inline fun Properties.requireString(key: String) =
  (this[key] as String?)
    ?.takeIf { it.isNotBlank() }
    ?: throw IllegalStateException("Required property $key is missing or blank!")

private inline fun Properties.requireInt(key: String) =
  requireString(key)
    .let {
      try {
        it.toInt() } catch (e: Throwable) {
        throw IllegalStateException("Required property $key must be an int value.")
      } }

