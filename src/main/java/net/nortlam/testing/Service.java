package net.nortlam.testing;

import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;
import com.mongodb.util.JSONParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import net.nortlam.testing.setup.MongoProvider;
import org.bson.Document;
import org.bson.types.ObjectId;

@Stateless
public class Service {

    private static final Logger LOG = Logger.getLogger(Service.class.getName());
    
    @EJB
    private MongoProvider provider;

    public Person findByEmail(String email) throws NotFoundException {
        MongoCollection<Document> collection = getDatabase().getCollection(MongoProvider.COLLECTION);
        Document document = collection.find(Filters.eq("email", email)).first();
        if(document == null) throw new NotFoundException("### Email not Found");
        
        
        return new Person(document);
    }
    
    public ObjectId create(Person person) {
        MongoCollection<Document> collection = getDatabase().getCollection(MongoProvider.COLLECTION);
        Document document = null;
        try {
//            document = (Document)JSON.parse(person.toString());
            document = Document.parse(person.toString());
            collection.insertOne(document);
            
        } catch(JSONParseException ex) {
            LOG.log(Level.SEVERE, "### JSON PARSE EXCEPTION:{0}", ex.getMessage());
        } catch(MongoWriteException ex) {
            LOG.log(Level.SEVERE, "### MONGO WRITE EXCEPTION:{0}", ex.getMessage());
        } catch(MongoWriteConcernException ex) {
            LOG.log(Level.SEVERE, "### MONGO WRITE CONCERN EXCEPTION:{0}", ex.getMessage());
        } catch(MongoException ex) {
            LOG.log(Level.SEVERE, "### MONGO EXCEPTION:{0}", ex.getMessage());
        }
        
        return document.getObjectId("_id");
    }
    
//    public Person findByID(String ID) throws NotFoundException {
//        Document query = new Document("_id", ID);
//        MongoCollection<Document> collection = getDatabase().getCollection(COLLECTION);
//        
//        boolean found = false; Document result = null;
//        for(Document document: collection.find(Filters.eq("_id", ID))) {
//            found = true; result = document; break;
//        }
//        
//        if(found) return Result
//        
//        
//    }
    
    private MongoDatabase getDatabase() {
        return provider.getClient().getDatabase(MongoProvider.DATABASE);
    }
    
}
