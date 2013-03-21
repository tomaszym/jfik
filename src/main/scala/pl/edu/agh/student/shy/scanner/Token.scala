package pl.edu.agh.student.shy.scanner

trait Token {
  def allowedNext(c: Char, cache: String = ""): Boolean

  def smallCaps_?(c: Char) = c  >= 'a' && c <= 'z'
  def number_?(c: Char) = c  >= '0' && c <= '9'

}