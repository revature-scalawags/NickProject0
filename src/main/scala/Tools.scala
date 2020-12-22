import spray.json._

object Tools {

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
}