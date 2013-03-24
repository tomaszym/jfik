package pl.edu.agh.student.shy.scanner

trait Scanner {

  type Lexeme = String

  val tokens: List[Token]
  
  val initial: State
  
  val initialTokens = tokens map {t => (t, initial)}
  
  def parse(str: String) = parseStep(str.tail, str.head, "", initialTokens, List())
  
  
  private def parseStep(text: String, next: Char, cache: String, allowedBefore: List[(Token, State)], acc: List[(Lexeme, Token)] ): List[(Lexeme, Token)] = {
    
    text match {
      case "" => acc.reverse // TODO bufor
      case more => {
        /* generate possible steps: */
        val allowed = allowedBefore map { case (token, state) =>
          (token, token.allowedNext(next, cache)(state)) 
        } filter { case (t, s) => s.isDefined }
        
        allowed match {
          // append new token, clear cache, reset states
          case Nil => parseStep(text.tail, text.head, "", state, tokens, (cache + next, activeTokens.head) :: acc)
                    
          case (token, state) :: tail => parseStep(text.tail, text.head, cache + next, state, allowed, acc ) 
        }
      }
    }
  }

}

case class NoInitialState() extends Exception("No initial state")