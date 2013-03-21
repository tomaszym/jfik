package pl.edu.agh.student.shy.scanner

trait Scanner {

  type Lexeme = String

  val tokens: List[Token]
  
  def parse(str: String) = parseStep(str.tail, str.head, "" , tokens, List())
  
  private def parseStep(text: String, next: Char, cache: String, activeTokens: List[Token], acc: List[(Lexeme, Token)] ): List[(Lexeme, Token)] = {
    text match {
      case "" => acc.reverse // TODO bufor
      case more => {
        val newActive = activeTokens filter { _.allowedNext(next, cache) }
        newActive match {
          case Nil => parseStep(text.tail, text.head, "", tokens, (cache + next, activeTokens.head) :: acc) // TODO ambiguity
          
          case head :: Nil => parseStep(text.tail, text.head, cache + next, newActive, acc)
          
          case head :: tail => parseStep(text.tail, text.head, cache + next, newActive, acc ) 
        }
      }
    }
  }

}
