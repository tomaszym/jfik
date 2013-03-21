package pl.edu.agh.student.shy.parser

trait Parser {

  type Lexeme = String 
  /** Token parsing priority.
   */
  val priority: List[Token]
  
  def parse(str: String) = parseStep(str.tail, str.head, "" , priority, List())
  
  private def parseStep(text: String, next: Char, cache: String, activeTokens: List[Token], acc: List[(Lexeme, Token)] ): List[(Lexeme, Token)] = {
    text match {
      case "" => acc.reverse // TODO bufor
      case more => {
        val newActive = activeTokens filter { _.allowedNext(next, cache) }
        newActive match {
          case Nil => parseStep(text.tail, text.head, "", priority, (cache + next, activeTokens.head) :: acc) // TODO ambiguity
          
          case head :: Nil => parseStep(text.tail, text.head, cache + next, newActive, acc)
          
          case head :: tail => parseStep(text.tail, text.head, cache + next, newActive, acc ) 
        }
      }
    }
  }

}
