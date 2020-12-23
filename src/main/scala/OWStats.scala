import spray.json._
import scala.io.StdIn.readLine
import scala.util.{Try,Success,Failure}
import scala.util.control.Exception.Finally
import org.mongodb.scala.bson.collection.immutable.Document

object OWStats extends App {

    println()
    println("This program retrieves and compares Overwatch profile statistics.")
    println("-----------------------------------------------------------------------------------------------")
    println()

    val platform = Tools.getPlatform

    println("Please enter a username.")

    val profile = OWApi.getProfileJson(platform)

    //val profileDoc = Document(profile.toString())

    val profileDoc = Document("Test" -> "Result")

    Mongo.add(profileDoc)

    println("Profile found! Now please enter the info you would like to see. You may choose from any of the following:")

    Tools.navigateJson(profile)
}