import org.scalatest.FunSuite
import pl.edu.agh.student.shy.scanner.ini.IniParser
import pl.edu.agh.student.shy.scanner.ini._
 
class IniScannerSuite extends FunSuite {
 
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