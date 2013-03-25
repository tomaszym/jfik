package pl.edu.agh.student.shy.scanner

abstract class State(line: Int, col: Int) {
  def eat_?(c: Char): Option[State] = None
}