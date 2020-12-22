import scala.io.Source._
import spray.json._
import scala.util.Try
import scala.io.StdIn.readLine
import scala.util.Success
import scala.util.Failure

object OWApi {
    // Use this to access the JSON for any profile given platform and name.
    def getProfileJson(platform: String): JsObject = {
        println()
        tryProfile(platform) match {
            case Success(value) => return value
            case Failure(exception) => println(); println("Profile not found, please try again."); getProfileJson(platform)
        }
    }

    // This is a helper method for getProfileJson
    def tryProfile(platform: String): Try[JsObject] = Try {
        val profile = readLine()
        val requestURL = s"https://ovrstat.com/stats/$platform/$profile"
        scala.io.Source.fromURL(requestURL).mkString.parseJson.asJsObject
    }
}