package example

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.common.fields.HelpLong
import java.io.InputStream
import java.io.OutputStream

fun main() {
  val command = Blast.blastn().apply {
    longHelp = HelpLong(true)
  }

  println("Executing command: ${command.toCliString()}")
  println()
  println("=== Command Output: =============================================")
  println()

  with(ProcessBuilder(*command.toCliArray()).start()) {
    inputStream.transferTo(System.out)
    errorStream.transferTo(System.err)
    require(waitFor() == 0)
  }
}

//
// transferTo (available in JDK 9+)
//

private const val BUFFER_SIZE = 8192
private fun InputStream.transferTo(out: OutputStream) {
  val buffer = ByteArray(BUFFER_SIZE)
  var read: Int
  while (this.read(buffer, 0, BUFFER_SIZE).also { read = it } >= 0) {
    out.write(buffer, 0, read)
  }
}