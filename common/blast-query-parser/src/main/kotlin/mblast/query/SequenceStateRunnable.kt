package mblast.query

/**
 * Runnable BLAST Query State Machine
 *
 * Extension of the [HeadlessSequenceStateMachine] type that provides a basic
 * run method that iterates through all the characters in the input query
 * sequence(s).
 *
 * Each character in the input query will be passed to the extension point
 * method [handleChar] which may be implemented by extending classes.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  1.0.0
 */
abstract class SequenceStateRunnable : HeadlessSequenceStateMachine(), Runnable {

  /**
   * Executes the process of iterating through all the characters in the input
   * query.
   */
  override fun run() {
    onQueryStart()

    var i = nextCharacter()
    while (i > -1) {
      shiftCurrentChar(i.toChar())

      index()

      handleChar(i)

      i = nextCharacter()
    }

    handleChar(-1)

    onQueryComplete()
  }


  /**
   * Outlet pipe for characters read from the input query.
   *
   * Each character read from the query (including the EOF `-1` value) will be
   * passed to this method after executing everything else in the backing state
   * machine.
   *
   * This means all event callback methods will be hit before this method is
   * called.
   *
   * @param c Character to handle.
   */
  protected open fun handleChar(c: Int) {}

}