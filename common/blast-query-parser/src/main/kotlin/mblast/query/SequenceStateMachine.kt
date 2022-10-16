package mblast.query

abstract class SequenceStateMachine : HeadlessSequenceStateMachine() {

  open fun run() {
    onQueryStart()

    var i = nextCharacter()
    while (i > -1) {
      shiftCurrentChar(i.toChar())

      index()

      pipe(i)

      i = nextCharacter()
    }

    pipe(-1)

    onQueryComplete()
  }


  /**
   * Outlet pipe for characters read from the input query.
   *
   * Each character read from the query (including the EOF `-1` value) will be
   * passed to this method before executing anything else in the state machine.
   */
  protected open fun pipe(c: Int) {}

}