package pl.edu.agh.student.shy

import scala.util.parsing.combinator.RegexParsers

trait Token {
  def allowedNext(c: Char, cache: String = ""): Boolean

  def smallCaps_?(c: Char) = c  >= 'a' && c <= 'z'
  def number_?(c: Char) = c  >= '0' && c <= '9'
}

case object Identifier extends Token {
  def allowedNext(c:Char, cache: String = "") = {

    cache match {
      case "" => smallCaps_?(c)
      case smth => smallCaps_?(c) || number_?(c)
    }

  }
}
case object Number extends Token {
  def allowedNext(c: Char, cache: String) = number_?(c)
}

case object BracketL extends Token {
  def allowedNext(c: Char, cache: String) = c == '['
}
case object BracketR extends Token {
  def allowedNext(c: Char, cache: String) = c == ']'
}

case object Eq  extends Token {
  def allowedNext(c: Char, cache: String) = c == '='
}
case object Error extends Token {
  def allowedNext(c: Char, cache: String) = cache == ""
}

object IniParser {

  val states = Identifier :: Number:: BracketL :: BracketR :: Eq :: Nil
  type Lexeme = String 
  var text = "[section] label = 12"
  def pullChar: Char = {
    val c = text.head
    text = text.tail
    c
  }
  
  def parse(str: String) = parseStep(str.tail, str.head, "" , states, List())
  
  private def parseStep(text: String, next: Char, cache: String, activeTokens: List[Token], acc: List[(Lexeme, Token)] ): List[(Lexeme, Token)] = {
    text match {
      case "" => acc.reverse // TODO bufor
      case more => {
        val newActive = activeTokens filter { _.allowedNext(next, cache) }
        newActive match {
          case Nil => parseStep(text.tail, text.head, "", states, (cache + next, activeTokens.head) :: acc) // TODO ambiguity
          
          case head :: Nil => parseStep(text.tail, text.head, cache + next, newActive, acc)
          
          case head :: tail => parseStep(text.tail, text.head, cache + next, newActive, acc ) 
        }
      }
    }
  }




}
