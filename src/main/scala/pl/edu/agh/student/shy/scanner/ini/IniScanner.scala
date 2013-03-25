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

case object StringToken extends Token {
  val t: Transition = (c, str, state) => if(str.head == '"') Some(state) else None
  override val isAccepting: (String, State) => Boolean = (str, state) => str.size > 1 && str.head == '"' && str.last == '"'
}

case object Error extends Token {
  val t: Transition = (c, str, state) => if(str == "") Some(state) else None }

case class InitialState(line: Int, col: Int) extends State(line, col) {
  override def eat_?(c: Char) = {
    if(List(' ').contains(c)) Some(InitialState(line, col+1))
    else if(List('\n').contains(c)) Some(InitialState(line+1, 0))
    else None
  }
}

object IniParser extends {

  val tokens = Identifier :: Number:: BracketL :: BracketR :: Eq :: Error :: Nil

  val initial = InitialState(0,0)

} with Scanner
