package pl.edu.agh.student.shy.parser.ini

import pl.edu.agh.student.shy.parser._

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

object IniParser extends Parser {

  val priority = Identifier :: Number:: BracketL :: BracketR :: Eq :: Nil



}
