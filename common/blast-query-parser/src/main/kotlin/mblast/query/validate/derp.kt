package mblast.query.validate

import mblast.query.pipe.SequenceFileWriter
import mblast.util.io.toIOStream
import java.io.File

private const val maxSeqLength = 12
private const val maxQueLength = 100
private const val maxSequences = 2

// Single sequence, Invalid characters, no defline
private const val seq1 = """hello"""

// Single sequence, Invalid characters, defline
private const val seq2 = """> defline
hello
"""

// Multi-sequence, empty trailing, no leading defline
private const val seq3 = """
  
  
  cat
  
  
  
  
  
> defline
catcatcatcat
"""

private const val seq4 = "666"

fun main() {
  val validator = ProteinSequenceValidationStream(
    maxSeqLength,
    maxSequences,
    maxQueLength,
    seq4.byteInputStream().toIOStream()
  )

  val writer = SequenceFileWriter("happy", File("/tmp"), validator)

  val res = writer.writeOutFiles()

  println(res.isSingleQuery)
  println(res.overallQueryFile)
  println(res.subQueryFiles)
}