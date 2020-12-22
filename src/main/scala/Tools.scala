import spray.json._
import scala.util.{Try,Success,Failure}
import scala.io.StdIn.readLine

object Tools {

  /**
      * This method helps the user navigate the json by iterating through the json like a tree. 
      *
      * @param root
      * @return 
      */
    def navigateJson(root : JsObject): JsObject = {
        Tools.listFields(root)
        println()
        val input = readLine()
        println()
        Tools.tryAsJson(root, input) match {
            case Success(result) => println("Input accepted. Please choose from the following:")
                navigateJson(result)

            case Failure(exception) => navigateHelper(root, input)
                return null
     }
    }

    def navigateHelper(root: JsObject, input: String) = {
        Tools.tryField(root, input) match {
            case Failure(exception) => println("Invalid input, please choose from the following:"); navigateJson(root)
            case Success(value) => println(input + " is " + value)
        }
    }

    /**
      * Small method for making sure user input for platform is valid.
      *
      * @return valid platform as a String.
      */
    def getPlatform: String = {
        println("Please enter a platform. (pc, psn, or xbl)")
        println()
        val platform = readLine()
        println()

        platform match {
            case "psn" => return platform
            case "xbl" => return platform
            case "pc" => return platform
            case _ => println("Invalid Platform, try again."); getPlatform
        }
    }

    /**
      * Simple method that returns the value of a single field in the json
      *
      * @param info - the key for the information you want out of the json
      * @param profile - player profile as a JsObject
      * @return the value associated with the key, "info"
      */
    def getField(info: String, profile: JsObject) = {
        profile.fields.get(info).get
    }

    /**
      * Starts that guides user through quickplay stats to find what they want.
      *
      * @param profile - player profile as a JsObject
      * @return
      */
    def quickPlayStats(profile: JsObject) = {
        profile.fields.get("quickPlayStats").get.asJsObject.fields.get("careerStats").get.asJsObject.fields.get("allHeroes").get
    }

    def listFields(json: JsObject) = {
      println(json.fields.keys.toString().substring(8,json.fields.keys.toString.length-1))
    }

    /**
      * This method tries to interpret the value associated with 'input'
      * as a JsObject. Used to see whether the value associated with a
      * key is 
      *
      * @param root - The JsObject to which the keys belong
      * @param input - key in the JsObject
      * @return result wrapped in either Success or Failure
      */
    def tryAsJson(root: JsObject, input: String): Try[JsObject] = Try {
        Tools.getField(input, root).asJsObject
    }

    /**
      * Simple method that just attempts to get the value associated with the given key.
      *
      * @param root The JsObject to which the keys belong
      * @param input The key we are checking exists
      * @return Success(value) if input is a key, Failure(exception) if input is not a key.
      */
    def tryField(root: JsObject, input: String): Try[JsValue] = Try {
        Tools.getField(input, root)
    }
}