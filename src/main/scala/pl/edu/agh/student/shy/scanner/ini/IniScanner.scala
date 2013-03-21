package pl.edu.agh.student.shy.scanner.ini

import pl.edu.agh.student.shy.scanner._

case object Identifier extends Token {
  def allowedNext(c:Char, cache: String = "") = cache match {
      case "" => smallCaps_?(c)
      case smth => smallCaps_?(c) || number_?(c)
    }
}
case object Number   extends Token { def allowedNext(c: Char, cache: String) = number_?(c) }
case object BracketL extends Token { def allowedNext(c: Char, cache: String) = c == '[' }
case object BracketR extends Token { def allowedNext(c: Char, cache: String) = c == ']' }
case object Eq       extends Token { def allowedNext(c: Char, cache: String) = c == '=' }
case object Error    extends Token { def allowedNext(c: Char, cache: String) = cache == "" }

object IniParser extends Scanner {

  val tokens = Identifier :: Number:: BracketL :: BracketR :: Eq :: Nil



}
