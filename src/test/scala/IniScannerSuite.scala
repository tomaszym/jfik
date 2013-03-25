import org.scalatest.FunSuite
import pl.edu.agh.student.shy.scanner.ini.IniParser
import pl.edu.agh.student.shy.scanner.ini._
 
class IniScannerSuite extends FunSuite {
 
  test("parses simple CORRECT synthetic file") {
	
    val txt = "[  section ]label=12"
    val res = IniParser.parse(txt)
    
    assert(res === List(
        ("[", BracketL),
        ("section", Identifier),
        ("]", BracketR),
        ("label", Identifier),
        ("=", Eq),
        ("12", Number)
    ))
  }
  test("ini file with string") {
	
    val txt = """[section]label="asdf""" // missing "
    val res = IniParser.parse(txt)
    
    assert(res === List(
        ("[", BracketL),
        ("section", Identifier),
        ("]", BracketR),
        ("label", Identifier),
        ("=", Eq),
        (""""asdf"""", StringToken)
    ))
  }
}