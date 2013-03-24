package pl.edu.agh.student.shy.scanner.ini

import pl.edu.agh.student.shy.scanner._

case object Identifier extends Token {
  val t: Transition = (c, cache, state) => cache match {
      case "" => if(c.isLetter) Some(state) else None
      case smth => if(c.isLetter || c.isDigit) Some(state) else None
    }
}
case object Number extends Token { 
  val t: Transition = (c, str, state) => if(c.isDigit) Some(state) else None }

case object BracketL extends Token {
  val t: Transition = (c, str, state) => if(c == '[') Some(state) else None }

case object BracketR extends Token {
  val t: Transition = (c, str, state) => if(c == ']') Some(state) else None }

case object Eq extends Token {
  val t: Transition = (c, str, state) => if(c == '=') Some(state) else None }

case object Error extends Token {
  val t: Transition = (c, str, state) => if(str == "") Some(state) else None }

case object InitialState extends State

object IniParser extends {

  val tokens = Identifier :: Number:: BracketL :: BracketR :: Eq :: Error :: Nil

  val initial = InitialState

} with Scanner
