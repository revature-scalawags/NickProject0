import spray.json._
import scala.io.StdIn.readLine
import scala.util.{Try,Success,Failure}
import scala.util.control.Exception.Finally

object OWStats extends App {
    println()
    println("This program retrieves and compares Overwatch profile statistics.")
    println("-----------------------------------------------------------------------------------------------")
    println()
    val platform = getPlatform

    println("Please enter a username.")
    val profile = OWApi.getProfileJson(platform)

    //TODO: Handle invalid inputs without throwing exception.
    println("Profile found! Now please enter the info you would like to see. You may choose from any of the following:")
    navigateJson(profile)

    /**
      * This method helps the user navigate the json by iterating through the json like a tree. 
      * 
      *
      * @param root
      * @return 
      */
    def navigateJson(root : JsObject): JsObject = {
        Tools.listFields(root)
        println()
        val input = readLine()
        println()
        getInfo(root, input) match {
            case Success(i) => println("Input accepted. Please choose from the following:")
                navigateJson(Tools.getField(input, root).asJsObject)

            case Failure(s) => println("The " + input + " is " + Tools.getField(input, root))
                return profile
     }
    }

    // Return type shouldnt be JsObject, should be string or int
    //def navigateHelper(input: String, root: JsObject): Try[JsObject] = Try {
        //Tools.getField(input, root)
    //}

   // def navigateHelperHelper(input: String, root: JsObject) () {
      //  navigateHelper(input,root) match {
     //       case Success => 
       //     case Failure =>
        //}
    //}

    /**
      * This method tries to interpret the value associated with 'input'
      * as a JsObject. This is used for match cases.
      *
      * @param json
      * @param input
      * @return result wrapped in either Success or Failure
      */
    def getInfo(json: JsObject, input: String): Try[JsObject] = Try {
        Tools.getField(input, json).asJsObject
    }

    /**
      * Small method for making sure user input is correct.
      *
      * @return valid platform
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
}