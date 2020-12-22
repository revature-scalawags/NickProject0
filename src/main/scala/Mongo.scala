import org.mongodb.scala._

object Mongo {
    def mongoTest = {
        val uri: String = "mongodb+srv://OWSTATS:<HpSWQi8QCB8TMYbk>@cluster0.rpxvc.mongodb.net/<OWSTATS>?retryWrites=true&w=majority"
        System.setProperty("org.mongodb.async.type", "netty")
        val client: MongoClient = MongoClient(uri)
        val db = client.getClusterDescription
        println(db)
    }
}