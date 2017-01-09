package net.nortlam.testing;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import net.nortlam.testing.setup.MongoProvider;
import org.bson.Document;

@Stateless
public class Service {

    private static final Logger LOG = Logger.getLogger(Service.class.getName());
    public static final String DATABASE = "people";
    public static final String COLLECTION = "persons";
    
    @EJB
    private MongoProvider provider;


    public Person findByEmail(String email) throws NotFoundException {
        Document query = new Document("emil", email);
        
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
        return provider.getClient().getDatabase(DATABASE);
    }
    
}
