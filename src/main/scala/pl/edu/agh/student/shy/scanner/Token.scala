package pl.edu.agh.student.shy.scanner

trait Token {
  type Transition = (Char, String, State) => Option[State]
//  def allowedNext(c: Char, cache: String = "")(implicit state: State): Option[State]
  val t: Transition
  val isAccepting: (String, State) => Boolean = (str, state) => true
}
