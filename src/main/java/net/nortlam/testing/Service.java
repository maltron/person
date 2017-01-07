package net.nortlam.testing;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
    
    
    public Person findByID(String ID) throws NotFoundException {
        Document query = new Document("_id", ID);
        MongoCollection<Document> collection = getDatabase().getCollection(COLLECTION);
        collection.find(query)
        
    }
    
    private MongoDatabase getDatabase() {
        return provider.getClient().getDatabase(DATABASE);
    }
    
}
