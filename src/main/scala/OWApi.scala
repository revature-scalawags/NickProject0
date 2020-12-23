import scala.io.Source._
import spray.json._
import scala.util.Try
import scala.io.StdIn.readLine
import scala.util.Success
import scala.util.Failure
import com.typesafe.scalalogging.LazyLogging

object OWApi extends LazyLogging{
    /**
      * This method returns a JsObject made from the player stats json which is given by the Overwatch API.
      * It also handles cases that the profile cannot be 
      *
      * @param platform The platform the profile plays on.
      * @return JsObject parsed from the json associated with requested player stats.
      */
    def getProfileJson(platform: String): JsObject = {
        println()
        tryProfile(platform) match {
            case Success(value) => println(); logger.info("Profile found, JsObject successfully created."); return value
            case Failure(exception) => println(); println("Profile not found, please try again."); logger.info("Profile not found. Prompting user to try again."); getProfileJson(platform)
        }
    }

    /**
      * Small helper method for getProfileJson. This method is the one which actually makes the call to the API.
      * The result returned is wrapped in either Success or Failure to avoid exceptions which would occur if the user tries
      * to access a profile that does not exist.
      * 
      * @param platform The platform the profile plays on.
      * @return Success(JsObject) if profile is found, Failure(Exception) if no profile is found.
      */
    def tryProfile(platform: String): Try[JsObject] = Try {
        val profile = readLine()
        val requestURL = s"https://ovrstat.com/stats/$platform/$profile"
        logger.info("Trying to find profile at " + requestURL)
        scala.io.Source.fromURL(requestURL).mkString.parseJson.asJsObject
    }
}