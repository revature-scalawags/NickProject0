import org.mongodb.scala._
import org.mongodb.scala.model._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model.UpdateOptions
import org.mongodb.scala.bson.BsonObjectId
import org.mongodb.scala.bson.BsonDocument
import com.mongodb.client.result.InsertOneResult
import com.mongodb.client.result.DeleteResult
import scala.io.StdIn.readLine
import scala.concurrent.Await
import scala.concurrent.duration.SECONDS
import scala.concurrent.duration.Duration
import com.typesafe.scalalogging.LazyLogging
import tour.Helpers._


object Mongo extends LazyLogging{

    /**
      * This method accesses the mongo database and adds the specified document to the collection.
      *
      * @param doc The document to be added to the collection.
      */
    def add(doc: Document) = {
       // System.setProperty("jdk.tls.client.protocols","TLSv1.2")
        //System.setProperty("https.protocols", "TLSv1.2")
        val uri: String = "mongodb+srv://OWSTATS:HpSWQi8QCB8TMYbk@cluster0.rpxvc.mongodb.net/OWSTATS?retryWrites=true&w=majority"
        //System.setProperty("org.mongodb.async.type", "netty")
        val client: MongoClient = MongoClient(uri)
        val db: MongoDatabase = client.getDatabase("OWSTATS")
        val col: MongoCollection[Document] = db.getCollection("OWSTATS")
        col.insertOne(doc).results()
        client.close()
        println("Document has been successfully added to the collection.")
    }
}