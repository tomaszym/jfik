package pl.edu.agh.student.shy.scanner

trait Token {
  def allowedNext(c: Char, cache: String = "")(implicit state: State): Option[State]
  def acceptingState(cache: String)(implicit state: State): Boolean
}
