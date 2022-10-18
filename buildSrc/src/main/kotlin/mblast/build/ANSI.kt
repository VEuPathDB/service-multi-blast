package mblast.build

object ANSI {
  const val RESET = "\u001B[0m"

  const val FG_BLACK = "\u001B[30m"
  const val FG_RED   = "\u001B[31m"
  const val FG_GREEN = "\u001B[32m"
  const val FG_YELLOW = "\u001B[33m"
  const val FG_BLUE = "\u001B[34m"
  const val FG_MAGENTA = "\u001B[35m"
  const val FG_CYAN = "\u001B[36m"
  const val FG_WHITE = "\u001B[37m"

  @JvmStatic
  fun fgBit(value: UByte) = "\u001B[38:5:${value}m"
}