@file:Suppress("NOTHING_TO_INLINE")

package mblast.build

import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.sftp.RemoteResourceInfo
import net.schmizz.sshj.sftp.SFTPClient
import net.schmizz.sshj.transport.verification.PromiscuousVerifier
import org.gradle.api.Project
import java.util.*


fun Project.CreateBlastDBDirectoryIfNotExists() {
  val blastDir = file("blastdb")
  blastDir.exists() && return

  println("\nDirectory ./blastdb does not exist, downloading blast files...")

  val props = getPropsOrCreateAndFail()

  println("  Connecting to ${props.sshHost}:${props.sshPort} over SSH")

  SSHClient().use { ssh ->
    ssh.addHostKeyVerifier(PromiscuousVerifier())
    ssh.connect(props.sshHost, props.sshPort)
    ssh.authPublickey(
      props.sshUser,
      if (props.sshPass == "") {
        println("  Using no password")
        ssh.loadKeys(props.sshPath)
      } else {
        println("  Using configured password")
        ssh.loadKeys(props.sshPath, props.sshPass)
      }
    )

    ssh.newSFTPClient().use { sftp ->

      println("  Listing available organisms...")

      val baseRemoteDir = "${props.filesRoot}/${props.siteName}/${props.siteBuild}"

      sftp.dirStream(baseRemoteDir)
        // Filter to only dirs that contain a "blast" sub-dir
        .filter { it.containsBlastDir(sftp) }
        // Filter to only dirs whose "blast" sub-dir is not empty.
        .filter { it.containsBlastFiles(sftp) }
        // We don't need to download the whole thing, just a few is enough
        .limit(5)
        // Get the path to the directory we are going to download into
        .map { Pair(it, file("blastdb/${props.siteName}/${props.siteBuild}/${it.name}/blast")) }
        // Create the directory path
        .peek { (_, dir) -> dir.mkdirs() }
        // Map down to a remote path and local path for blast dirs
        .map { (res, dir) -> Pair("${res.path}/blast", dir) }
        // Flat map our remote dirs into file entries inside those dirs
        .flatMap { (res, dir) -> sftp.fileStream(res).map { Pair(it, dir) } }
        // Log!
        .peek { (res, _) -> println("  downloading file ${res.path}") }
        // Download the files
        .forEach { (res, dir) -> sftp.get(res.path, "${dir.path}/${res.name}") }
    }
  }
}

private inline fun RemoteResourceInfo.containsBlastDir(sftp: SFTPClient) =
  sftp.ls(path)
    .firstOrNull { it.name == "blast" }
    ?.let { true }
    ?: false

private inline fun RemoteResourceInfo.containsBlastFiles(sftp: SFTPClient) =
  !sftp.ls("$path/blast").isNullOrEmpty()

private inline fun SFTPClient.dirStream(path: String) =
  ls(path)
    .stream()
    .filter { it.isDirectory }

private inline fun SFTPClient.fileStream(path: String) =
  ls(path)
    .stream()
    .filter { it.isRegularFile }
