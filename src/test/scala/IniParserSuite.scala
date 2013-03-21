import org.scalatest.FunSuite
import pl.edu.agh.student.shy.parser.ini.IniParser
import pl.edu.agh.student.shy.parser.ini._
 
class IniParserSuite extends FunSuite {
 
  test("parses simple synthetic file") {
	
    val txt = "[section]label=12"
    val res = IniParser.parse(txt)
    
    assert(res === List(
        ("[", BracketL),
        ("section", Identifier),
        ("]", BracketR),
        ("label", Number),
        ("=", Eq),
        ("12", Number)
    ))
    
  }
}