import spray.json._
import org.scalatest._
import org.mongodb.scala.bson.collection.immutable.Document
import com.mongodb.client.result.InsertOneResult
import org.scalatest.funsuite.AnyFunSuite

class Test extends AnyFunSuite {
    //test 1
    test("Tools.getField method works for a valid profile") {

        val requestURL = s"https://ovrstat.com/stats/psn/ditty40"
        val profile = scala.io.Source.fromURL(requestURL).mkString.parseJson.asJsObject

        assert(Tools.getField("name", profile).toString.substring(1, 8) == "ditty40")
    }

    //test 2
    
}