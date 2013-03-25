package pl.edu.agh.student.shy.scanner

trait Scanner {

  type Lexeme = String

  val tokens: List[Token]
  
  val initial: State
  
  val initialTokens = tokens map {t => (t, initial)}
  
  def parse(str: String) = parseStep(str.tail, str.head, "", initialTokens, List())
  
  type TokenAtState = (Token, State)
  /**
   * @param text more to be scanned
   * @param next current character
   * @param allowedBefore current state of scanner
   * @param acc accumulator with scanned tokens
   */
  private def parseStep(text: String, next: Char, cache: String, allowedBefore: List[TokenAtState], acc: List[(Lexeme, Token)] ): List[(Lexeme, Token)] = {
     /**
     * Helper method for applying restrictions.
     * @param c next character
     * @param cache 
     * @param tokens list of tokens with states to be filtered
     */
    def filterTokens(c: Char, cache: String, tokens: List[TokenAtState]) = {
      tokens map { case (token, state) =>
          (token, token.t(next, cache, state)) 
        } filter { case (t, s) => s.isDefined } map { case (t,s) => (t, s.get)} 
    }
    def feedStates(c: Char, states: List[TokenAtState]): List[TokenAtState] =
      states .map    { case (t, s) => (t, s.eat_?(c))} 
             .filter { case (t, opt) => opt.isDefined } 
             .map    { case (t, opt) => (t, opt.get)}

    text match {
      case "" => ((cache+next, allowedBefore.head._1) :: acc).reverse
      case more => {
        /* generate possible steps: */
        val allowed = filterTokens(next, cache, allowedBefore)
        allowed match {
          // cant add any more chars to current available states, there are two ways:
          // a) char might be a neutral one for a state, eg. whitespaces
          // b) we end the current token and start building next
          // problem:  spacje wymieszane z identyfikatorem - feeding tylko dla pustego kesza?
          case Nil => feedStates(next, allowedBefore) match {
            case Nil => parseStep(text.tail, text.head, next.toString, filterTokens(next, "", initialTokens), (cache, allowedBefore.head._1) :: acc)
            case states => parseStep(text.tail, text.head, )
          } 
                    
          case tokens => parseStep(text.tail, text.head, cache + next, tokens, acc ) 
        }
      }
    }

  }

}

case class NoInitialState() extends Exception("No initial state")